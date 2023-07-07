package work.mayflower.ms.upgate.integration.exception

enum class ErrorConstants(val message: String, val code: String) {
    CANT_PROCESS_PAYMENT("Can't process payment, please retry later.", "CANT_PROCESS_PAYMENT"),
    PAYMENT_NOT_FOUND("Requested payment not found", "PAYMENT_NOT_FOUND"),
    INVALID_FIELDS("One or more fields invalid", "INVALID_FIELDS"),
    HTTP_METHOD_NOT_SUPPORTED("HTTP method not supported", "HTTP_METHOD_NOT_SUPPORTED"),
    UNEXPECTED_ERROR("Unexpected error occurred", "UNEXPECTED_ERROR")
}