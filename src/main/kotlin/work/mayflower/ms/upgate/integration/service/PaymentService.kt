package work.mayflower.ms.upgate.integration.service

import org.springframework.stereotype.Service
import work.mayflower.ms.upgate.integration.client.UpgateClient
import work.mayflower.ms.upgate.integration.config.CallbackUrlsConfig
import work.mayflower.ms.upgate.integration.dao.PaymentRepository
import work.mayflower.ms.upgate.integration.exception.BadGatewayException
import work.mayflower.ms.upgate.integration.exception.ErrorConstants.CANT_PROCESS_PAYMENT
import work.mayflower.ms.upgate.integration.exception.ErrorConstants.PAYMENT_NOT_FOUND
import work.mayflower.ms.upgate.integration.exception.NotFoundException
import work.mayflower.ms.upgate.integration.mapper.toApiResponse
import work.mayflower.ms.upgate.integration.mapper.toEntity
import work.mayflower.ms.upgate.integration.mapper.toUpgateDto
import work.mayflower.ms.upgate.integration.model.CallbackRequest
import work.mayflower.ms.upgate.integration.model.InitializePaymentRequest
import work.mayflower.ms.upgate.integration.model.InitializePaymentResponse

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val upgateClient: UpgateClient,
    private val callbackUrlsConfig: CallbackUrlsConfig
) {

    fun initializePayment(request: InitializePaymentRequest): InitializePaymentResponse {
        val upgateResponse = try {
            upgateClient.initializePayment(
                request.toUpgateDto(
                    callbackUrlsConfig.success,
                    callbackUrlsConfig.failure
                )
            ).toApiResponse()
        } catch (ex: Exception) {
            throw BadGatewayException(CANT_PROCESS_PAYMENT.message, CANT_PROCESS_PAYMENT.code)
        }

        paymentRepository.save(request.toEntity(upgateResponse.data.paymentId))

        return upgateResponse
    }

    fun callback(request: CallbackRequest) {
        val payment =
            paymentRepository.findById(request.payment.paymentId)
                .orElseThrow {
                    throw NotFoundException(PAYMENT_NOT_FOUND.message, PAYMENT_NOT_FOUND.code)
                }

        payment.transactionStatus = request.transactionStatus
        paymentRepository.save(payment)
    }
}