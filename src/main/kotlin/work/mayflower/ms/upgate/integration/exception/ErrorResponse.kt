package work.mayflower.ms.upgate.integration.exception

data class ErrorResponse(var message: String, var code: String, var validationErrors: List<String?>? = null)