package work.mayflower.ms.upgate.integration.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.validator.constraints.Length

@JsonNaming(SnakeCaseStrategy::class)
data class ProductInfo(
    var productId: String? = null,
    var merchantProductId: String,
    var productPrice: String,
    @field:Length(min = 1, max = 256)
    var productName: String,
    @field:Length(min = 1, max = 256)
    var productDescription: String,
    var productType: ProductType,
    var chargeIntervalValue: Int? = null,
    var chargeInterval: String? = null,
    var isTrial: Boolean? = null,
    var trialIntervalValue: Int? = null,
    var trialInterval: String? = null
)