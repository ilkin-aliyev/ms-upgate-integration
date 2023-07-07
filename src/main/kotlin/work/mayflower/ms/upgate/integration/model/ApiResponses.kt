package work.mayflower.ms.upgate.integration.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal

data class InitializePaymentResponse(
    var type: String,
    var data: UpgatePaymentInfo
)

@JsonNaming(SnakeCaseStrategy::class)
data class UpgatePaymentInfo(
    var paymentId: String,
    var paymentType: PaymentType,
    var paymentMethod: PaymentMethod,
    var createdAt: String,
    var merchantId: String,
    var merchantPaymentId: String? = null,
    var merchantCustomerId: String,
    var email: String,
    var amount: BigDecimal,
    var countryCode: String,
    var currencyCode: String,
    @JsonProperty("forced_3d") var forced3d: Boolean? = false,
    var paymentTokenId: String? = null,
    var language: String? = null,
    var session: SessionInfo,
    var products: List<ProductInfo>
)

@JsonNaming(SnakeCaseStrategy::class)
data class SessionInfo(
    var createdAt: String,
    var expiresAt: String,
    var redirectUrl: String
)