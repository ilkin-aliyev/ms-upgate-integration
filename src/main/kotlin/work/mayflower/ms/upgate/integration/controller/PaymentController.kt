package work.mayflower.ms.upgate.integration.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import work.mayflower.ms.upgate.integration.model.CallbackRequest
import work.mayflower.ms.upgate.integration.model.InitializePaymentRequest
import work.mayflower.ms.upgate.integration.service.PaymentService
import javax.validation.Valid

@RestController
@RequestMapping("v1/payments")
@CrossOrigin
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping("/initialize")
    fun initializePayment(@RequestBody @Valid request: InitializePaymentRequest) =
        paymentService.initializePayment(request)

    @PostMapping("/callback")
    fun callback(@RequestBody @Valid request: CallbackRequest) = paymentService.callback(request)
}