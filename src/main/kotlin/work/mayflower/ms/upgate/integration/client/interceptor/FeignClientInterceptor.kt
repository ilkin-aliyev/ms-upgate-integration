package work.mayflower.ms.upgate.integration.client.interceptor

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class FeignClientInterceptor(@Value("\${client.upgate.api-key}") private val apiKey: String) : RequestInterceptor {

    override fun apply(requestTemplate: RequestTemplate) {
        requestTemplate.header(API_KEY_HEADER, apiKey)
    }

    companion object {
        private const val API_KEY_HEADER = "X-Api-Key"
    }
}