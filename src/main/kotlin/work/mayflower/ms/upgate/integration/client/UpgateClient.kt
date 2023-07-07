package work.mayflower.ms.upgate.integration.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import work.mayflower.ms.upgate.integration.model.InitializePaymentRequestDto
import work.mayflower.ms.upgate.integration.model.InitializePaymentResponseDto

@FeignClient(name = "upgate-client", url = "\${client.upgate.url}")
interface UpgateClient {

    @PostMapping("/v1/sale")
    fun initializePayment(@RequestBody dto: InitializePaymentRequestDto): InitializePaymentResponseDto
}