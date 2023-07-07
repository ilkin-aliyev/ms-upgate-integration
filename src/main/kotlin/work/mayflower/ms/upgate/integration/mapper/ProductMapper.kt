package work.mayflower.ms.upgate.integration.mapper

import work.mayflower.ms.upgate.integration.model.ProductDto
import work.mayflower.ms.upgate.integration.model.ProductInfo

fun ProductInfo.toUpgateDto() =
    ProductDto(
        productId,
        merchantProductId,
        productPrice,
        productName,
        productDescription,
        productType,
        chargeIntervalValue,
        chargeInterval
    )

fun ProductDto.toApiResponse() =
    ProductInfo(
        productId,
        merchantProductId,
        productPrice,
        productName,
        productDescription,
        productType,
        chargeIntervalValue
    )