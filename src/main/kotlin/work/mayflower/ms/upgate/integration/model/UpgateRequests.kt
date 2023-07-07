package work.mayflower.ms.upgate.integration.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class InitializePaymentRequestDto(
    var paymentMethod: PaymentMethod,
    var merchantPaymentId: String?,
    var merchantCustomerId: String,
    var email: String,
    var amount: String,
    var language: String,
    var countryCode: String,
    var currencyCode: String,
    @JsonProperty("forced_3d") var forced3d: Boolean? = false,
    var successUrl: String,
    var failureUrl: String,
    var paymentTokenId: String? = null,
    var products: List<ProductDto>
)