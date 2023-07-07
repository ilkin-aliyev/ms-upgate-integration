package work.mayflower.ms.upgate.integration.exception

class BadGatewayException(override val message: String, val code: String) : RuntimeException(message)
class NotFoundException(override val message: String, val code: String) : RuntimeException(message)