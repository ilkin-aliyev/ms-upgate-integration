package work.mayflower.ms.upgate.integration.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class ProductDto(
    var productId: String? = null,
    var merchantProductId: String,
    var productPrice: String,
    var productName: String,
    var productDescription: String,
    var productType: ProductType,
    var chargeIntervalValue: Int? = null,
    var chargeInterval: String? = null,
    var isTrial: Boolean? = null,
    var trialIntervalValue: Int? = null,
    var trialInterval: String? = null
)
