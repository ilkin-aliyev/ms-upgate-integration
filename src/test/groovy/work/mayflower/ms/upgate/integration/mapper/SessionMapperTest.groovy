package work.mayflower.ms.upgate.integration.mapper

import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification
import work.mayflower.ms.upgate.integration.model.SessionDto

class SessionMapperTest extends Specification {
    private random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestToUpgateDto"() {
        given:
        def dto = random.nextObject(SessionDto)

        when:
        def response = use(SessionMapperKt) {
            dto.toApiResponse()
        }

        then:
        response.createdAt == dto.createdAt
        response.expiresAt == dto.expiresAt
        response.redirectUrl == dto.redirectUrl
    }
}
