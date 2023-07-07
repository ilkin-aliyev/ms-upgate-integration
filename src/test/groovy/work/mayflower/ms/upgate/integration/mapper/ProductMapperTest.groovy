package work.mayflower.ms.upgate.integration.mapper

import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification
import work.mayflower.ms.upgate.integration.model.ProductDto
import work.mayflower.ms.upgate.integration.model.ProductInfo

class ProductMapperTest extends Specification {
    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestToUpgateDto"() {
        given:
        def request = random.nextObject(ProductInfo)

        when:
        def dto = use(ProductMapperKt) {
            request.toUpgateDto()
        }

        then:
        dto.productId == request.productId
        dto.merchantProductId == request.merchantProductId
        dto.productPrice == request.productPrice
        dto.productName == request.productName
        dto.productDescription == request.productDescription
        dto.productType == request.productType
        dto.chargeIntervalValue == request.chargeIntervalValue
        dto.chargeInterval == request.chargeInterval
    }

    def "TestToApiResponse"() {
        given:
        def dto = random.nextObject(ProductDto)

        when:
        def response = use(ProductMapperKt) {
            dto.toApiResponse()
        }

        then:
        response.productId == dto.productId
        response.merchantProductId == dto.merchantProductId
        response.productPrice == dto.productPrice
        response.productName == dto.productName
        response.productDescription == dto.productDescription
        response.productType == dto.productType
        response.chargeIntervalValue == dto.chargeIntervalValue
    }
}
