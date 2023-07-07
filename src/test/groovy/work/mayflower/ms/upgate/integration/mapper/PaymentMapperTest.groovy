package work.mayflower.ms.upgate.integration.mapper

import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification
import work.mayflower.ms.upgate.integration.model.InitializePaymentRequest

class PaymentMapperTest extends Specification {
    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestToEntity"() {
        given:
        def id = random.nextObject(String)
        def request = random.nextObject(InitializePaymentRequest)

        when:
        def entity = use(PaymentMapperKt) {
            request.toEntity(id)
        }

        then:
        entity.id == id
        entity.amount == request.amount
        entity.currencyCode == request.currencyCode
        entity.countryCode == request.countryCode
        entity.customerId == request.merchantCustomerId
        entity.paymentMethod == request.paymentMethod
    }

    def "TestToUpgateDto"() {
        given:
        def successCallbackUrl = random.nextObject(String)
        def failureCallbackUrl = random.nextObject(String)
        def request = random.nextObject(InitializePaymentRequest)

        when:
        def dto = use(PaymentMapperKt) {
            request.toUpgateDto(successCallbackUrl, failureCallbackUrl)
        }

        then:
        dto.paymentMethod == request.paymentMethod
        dto.merchantCustomerId == request.merchantCustomerId
        dto.email == request.email
        dto.amount == request.amount.toString()
        dto.language == request.language
        dto.countryCode == request.countryCode
        dto.currencyCode == request.currencyCode
        dto.forced3d == request.forced3d
        dto.successUrl == successCallbackUrl
        dto.failureUrl == failureCallbackUrl
        dto.paymentTokenId == request.paymentTokenId
    }
}
