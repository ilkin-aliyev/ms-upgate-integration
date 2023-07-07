package work.mayflower.ms.upgate.integration.mapper

import work.mayflower.ms.upgate.integration.model.InitializePaymentResponse
import work.mayflower.ms.upgate.integration.model.InitializePaymentResponseDto
import work.mayflower.ms.upgate.integration.model.UpgatePaymentDto
import work.mayflower.ms.upgate.integration.model.UpgatePaymentInfo

fun InitializePaymentResponseDto.toApiResponse() =
    InitializePaymentResponse(
        type = type,
        data = data.toApiResponse()
    )

private fun UpgatePaymentDto.toApiResponse() =
    UpgatePaymentInfo(
        paymentId = paymentId,
        paymentType = paymentType,
        paymentMethod = paymentMethod,
        createdAt = createdAt,
        merchantId = merchantId,
        merchantPaymentId = merchantPaymentId,
        merchantCustomerId = merchantCustomerId,
        email = email,
        amount = amount.toBigDecimal(),
        countryCode = countryCode,
        currencyCode = currencyCode,
        forced3d = forced3d,
        paymentTokenId = paymentTokenId,
        language = language,
        products = products.map { it.toApiResponse() }.toList(),
        session = session.toApiResponse()
    )
