package work.mayflower.ms.upgate.integration.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal
import javax.validation.constraints.Email

@JsonNaming(SnakeCaseStrategy::class)
data class InitializePaymentRequest(
    var paymentMethod: PaymentMethod,
    var merchantPaymentId: String?,
    @field:Length(min = 1, max = 64)
    var merchantCustomerId: String,
    @field:Email
    var email: String,
    var amount: BigDecimal,
    var language: String,
    var countryCode: String,
    var currencyCode: String,
    @JsonProperty("forced_3d") var forced3d: Boolean? = false,
    var paymentTokenId: String?,
    var products: MutableList<ProductInfo>
)

@JsonNaming(SnakeCaseStrategy::class)
data class CallbackRequest(
    var type: String,
    @field:Length(min = 1, max = 64)
    var transactionId: String,
    var transactionStatus: TransactionStatus,
    var createdAt: String,
    var responseCode: Int,
    var responseText: String,
    var transactionDetails: TransactionDetails,
    var paymentContext: PaymentContext?,
    var paymentDetails: PaymentDetails?,
    var payment: PaymentMetaData
)

@JsonNaming(SnakeCaseStrategy::class)
data class TransactionDetails(
    var threeDsAuthenticationValue: String?,
    var threeDsProcessor: String?,
    var threeDsServerTransactionId: String?,
    var threeDsEci: String?,
    var threeDsStatus: String?,
    var threeDsChallengeType: String?,
    var threeDsVersion: String?,
    var processorMerchantAccountId: String?,
    var processorTransactionId: String?,
    var processor: String,
    var processorResponseCode: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class PaymentContext(
    var browserUserAgent: String?,
    var browserScreenHeight: String?,
    var browserColorDepth: String?,
    var browserLanguage: String?,
    var browserTimezoneOffset: String?,
    var browserScreenWidth: String?,
    var challengeWindowSize: String?,
    var browserHasJsEnabled: String?,
    var browserHasJavaEnabled: String?,
    var browserAcceptHeader: String?,
    var ip: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class PaymentDetails(
    var paymentTokenId: String,
    var cardScheme: String,
    var cardTokenId: String,
    var cardBin: String,
    var cardLastFourDigits: String,
    var customerFullName: String,
    var cardExpiryMonth: String,
    var cardExpiryYear: String
)

@JsonNaming(SnakeCaseStrategy::class)
data class PaymentMetaData(
    @field:Length(min = 1, max = 64)
    var paymentId: String,
    var paymentMethod: PaymentMethod,
    @field:Length(min = 1, max = 64)
    var merchantId: String,
    var merchantPaymentId: String?,
    @field:Length(min = 1, max = 64)
    var merchantCustomerId: String,
    @field:Email
    var email: String,
    var amount: String,
    var language: String,
    @field:Length(min = 2, max = 2)
    var countryCode: String,
    @field:Length(min = 3, max = 3)
    var currencyCode: String,
    @JsonProperty("forced_3d") var forced3d: Boolean? = false,
    var successUrl: String,
    var failureUrl: String,
    var paymentTokenId: String? = null,
    var products: List<ProductDto>
)