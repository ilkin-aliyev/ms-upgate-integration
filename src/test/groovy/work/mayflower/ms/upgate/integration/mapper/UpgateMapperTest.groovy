package work.mayflower.ms.upgate.integration.mapper

import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification
import work.mayflower.ms.upgate.integration.model.InitializePaymentResponseDto

class UpgateMapperTest extends Specification {
    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestToApiResponse"() {
        given:
        def dto = random.nextObject(InitializePaymentResponseDto)
        dto.data.amount = "9.99"

        when:
        def response = use(UpgateMapperKt) {
            dto.toApiResponse()
        }

        then:
        response.type == dto.type
        response.data.paymentId == dto.data.paymentId
        response.data.paymentType == dto.data.paymentType
        response.data.paymentMethod == dto.data.paymentMethod
        response.data.createdAt == dto.data.createdAt
        response.data.merchantId == dto.data.merchantId
        response.data.merchantPaymentId == dto.data.merchantPaymentId
        response.data.merchantCustomerId == dto.data.merchantCustomerId
        response.data.email == dto.data.email
        response.data.countryCode == dto.data.countryCode
        response.data.currencyCode == dto.data.currencyCode
        response.data.forced3d == dto.data.forced3d
        response.data.paymentTokenId == dto.data.paymentTokenId
        response.data.language == dto.data.language
        response.data.amount == dto.data.amount.toBigDecimal()
    }
}
