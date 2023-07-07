package work.mayflower.ms.upgate.integration.controller

import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import work.mayflower.ms.upgate.integration.exception.ErrorHandler
import work.mayflower.ms.upgate.integration.model.*
import work.mayflower.ms.upgate.integration.service.PaymentService

import static java.math.BigDecimal.ONE
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static work.mayflower.ms.upgate.integration.model.PaymentMethod.CARD
import static work.mayflower.ms.upgate.integration.model.PaymentType.SALE
import static work.mayflower.ms.upgate.integration.model.ProductType.SUBSCRIPTION

class PaymentControllerTest extends Specification {
    private PaymentService paymentService
    private MockMvc mockMvc

    void setup() {
        paymentService = Mock()
        def paymentController = new PaymentController(paymentService)
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestInitializePayment"() {
        given:
        def baseUrl = "/v1/payments"
        def requestJson = ''' {
                    "payment_method": "CARD",
                    "merchant_payment_id": "1",
                    "merchant_customer_id": "2",
                    "email": "test@gmail.com",
                    "amount": 1,
                    "language": "4",
                    "country_code": "5",
                    "currency_code": "6",
                    "forced_3d": false,
                    "payment_token_id": 7,
                    "products": [
                        {
                            "product_id": "10",
                            "merchant_product_id": "20",
                            "product_price": "30",
                            "product_name": "40",
                            "product_description": "50",
                            "charge_interval_value": 1,
                            "charge_interval": "60",
                            "is_trial": true,
                            "trial_interval_value": 2,
                            "trial_interval": "70",
                            "product_type": "SUBSCRIPTION"
                        }
                    ]
        }
        '''

        def responseJson = ''' {
                    "type": "100",
                    "data": 
                        {
                            "payment_id": "1",
                            "payment_type": "SALE",
                            "payment_method": "CARD",
                            "created_at": "2",
                            "merchant_id": "3",
                            "merchant_payment_id": "4",
                            "merchant_customer_id": "5",
                            "email": "6",
                            "amount": 1,
                            "country_code": "7",
                            "currency_code": "8",
                            "forced_3d": false,
                            "payment_token_id": "9",
                            "language": "10",
                            "session": {
                                "created_at": "10",
                                "expires_at": "11",
                                "redirect_url": "12"
                            },
                            "products": [
                           { 
                            "product_id": "10",
                            "merchant_product_id": "20",
                            "product_price": "30",
                            "product_name": "40",
                            "product_description": "50",
                            "charge_interval_value": 1,
                            "charge_interval": "60",
                            "is_trial": true,
                            "trial_interval_value": 2,
                            "trial_interval": "70",
                            "product_type": "SUBSCRIPTION"
                           }
                    ]
                }
        }
        '''

        def product = new ProductInfo("10", "20", "30", "40", "50", SUBSCRIPTION, 1, "60", true, 2, "70")

        def request = new InitializePaymentRequest(
                CARD, "1", "2", "test@gmail.com", ONE, "4", "5", "6", false, "7", [product]
        )

        def expectedResponse = new InitializePaymentResponse(
                "100",
                new UpgatePaymentInfo(
                        "1", SALE, CARD, "2", "3", "4", "5", "6", ONE, "7", "8", false, "9", "10",
                        new SessionInfo("10", "11", "12"),
                        [product]
                )
        )

        when:
        def result = mockMvc.perform(post("$baseUrl/initialize")
                .contentType(APPLICATION_JSON)
                .content(requestJson)
        ).andReturn()

        then:
        1 * paymentService.initializePayment(request) >> expectedResponse

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(responseJson, response.getContentAsString(), false)
    }
}
