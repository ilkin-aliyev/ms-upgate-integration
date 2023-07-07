package work.mayflower.ms.upgate.integration.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class InitializePaymentResponseDto(
    var type: String,
    var data: UpgatePaymentDto
)

@JsonNaming(SnakeCaseStrategy::class)
data class UpgatePaymentDto(
    var paymentId: String,
    var paymentType: PaymentType,
    var paymentMethod: PaymentMethod,
    var createdAt: String,
    var merchantId: String,
    var merchantPaymentId: String? = null,
    var merchantCustomerId: String,
    var email: String,
    var amount: String,
    var countryCode: String,
    var currencyCode: String,
    var successUrl: String,
    var failureUrl: String,
    @JsonProperty("forced_3d") var forced3d: Boolean? = false,
    var paymentTokenId: String? = null,
    var language: String? = null,
    var session: SessionDto,
    var products: List<ProductDto>
)

@JsonNaming(SnakeCaseStrategy::class)
data class SessionDto(
    var createdAt: String,
    var expiresAt: String,
    var redirectUrl: String
)