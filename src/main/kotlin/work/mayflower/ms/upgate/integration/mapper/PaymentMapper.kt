package work.mayflower.ms.upgate.integration.mapper

import work.mayflower.ms.upgate.integration.dao.PaymentEntity
import work.mayflower.ms.upgate.integration.model.InitializePaymentRequest
import work.mayflower.ms.upgate.integration.model.InitializePaymentRequestDto

fun InitializePaymentRequest.toEntity(id: String) =
    PaymentEntity(
        id = id,
        amount = amount,
        currencyCode = currencyCode,
        countryCode = countryCode,
        customerId = merchantCustomerId,
        paymentMethod = paymentMethod
    )

fun InitializePaymentRequest.toUpgateDto(successCallbackUrl: String, failureCallbackUrl: String) =
    InitializePaymentRequestDto(
        paymentMethod = paymentMethod,
        merchantPaymentId = merchantPaymentId,
        merchantCustomerId = merchantCustomerId,
        email = email,
        amount = amount.toString(),
        language = language,
        countryCode = countryCode,
        currencyCode = currencyCode,
        forced3d = forced3d,
        successUrl = successCallbackUrl,
        failureUrl = failureCallbackUrl,
        paymentTokenId = paymentTokenId,
        products = products.map { it.toUpgateDto() }.toList()
    )
