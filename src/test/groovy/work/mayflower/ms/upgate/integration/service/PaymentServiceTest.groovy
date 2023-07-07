package work.mayflower.ms.upgate.integration.service

import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification
import work.mayflower.ms.upgate.integration.client.UpgateClient
import work.mayflower.ms.upgate.integration.config.CallbackUrlsConfig
import work.mayflower.ms.upgate.integration.dao.PaymentEntity
import work.mayflower.ms.upgate.integration.dao.PaymentRepository
import work.mayflower.ms.upgate.integration.exception.BadGatewayException
import work.mayflower.ms.upgate.integration.exception.NotFoundException
import work.mayflower.ms.upgate.integration.mapper.PaymentMapperKt
import work.mayflower.ms.upgate.integration.model.CallbackRequest
import work.mayflower.ms.upgate.integration.model.InitializePaymentRequest
import work.mayflower.ms.upgate.integration.model.InitializePaymentResponseDto

import static work.mayflower.ms.upgate.integration.exception.ErrorConstants.CANT_PROCESS_PAYMENT
import static work.mayflower.ms.upgate.integration.exception.ErrorConstants.PAYMENT_NOT_FOUND

class PaymentServiceTest extends Specification {
    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    private PaymentRepository paymentRepository
    private UpgateClient upgateClient
    private CallbackUrlsConfig callbackUrlsConfig
    private PaymentService paymentService

    void setup() {
        paymentRepository = Mock()
        upgateClient = Mock()
        callbackUrlsConfig = new CallbackUrlsConfig("mock success url", "mock failure url")
        paymentService = new PaymentService(paymentRepository, upgateClient, callbackUrlsConfig)
    }

    def "TestCallback success"() {
        given:
        def request = random.nextObject(CallbackRequest)
        def entity = random.nextObject(PaymentEntity)

        when:
        paymentService.callback(request)

        then:
        1 * paymentRepository.findById(request.payment.paymentId) >> Optional.of(entity)
        1 * paymentRepository.save(entity)
    }

    def "TestCallback error when payment not found"() {
        given:
        def request = random.nextObject(CallbackRequest)

        when:
        paymentService.callback(request)

        then:
        1 * paymentRepository.findById(request.payment.paymentId) >> Optional.empty()
        0 * paymentRepository.save()

        NotFoundException ex = thrown()
        ex.message == PAYMENT_NOT_FOUND.message
        ex.code == PAYMENT_NOT_FOUND.code
    }

    def "TestInitializePayment success"() {
        given:
        def request = random.nextObject(InitializePaymentRequest)

        def dto = use(PaymentMapperKt) {
            request.toUpgateDto(callbackUrlsConfig.success, callbackUrlsConfig.failure)
        }

        def upgateResponse = random.nextObject(InitializePaymentResponseDto)
        upgateResponse.data.amount = "1"

        def entity = use(PaymentMapperKt) {
            request.toEntity(upgateResponse.data.paymentId)
        }

        when:
        paymentService.initializePayment(request)

        then:
        1 * upgateClient.initializePayment(dto) >> upgateResponse
        1 * paymentRepository.save(entity)
    }

    def "TestInitializePayment error from UpGate"() {
        given:
        def request = random.nextObject(InitializePaymentRequest)

        def dto = use(PaymentMapperKt) {
            request.toUpgateDto(callbackUrlsConfig.success, callbackUrlsConfig.failure)
        }

        when:
        paymentService.initializePayment(request)

        then:
        1 * upgateClient.initializePayment(dto) >> { throw new Exception("mock message") }
        0 * paymentRepository.save()

        BadGatewayException ex = thrown()
        ex.message == CANT_PROCESS_PAYMENT.message
        ex.code == CANT_PROCESS_PAYMENT.code
    }
}
