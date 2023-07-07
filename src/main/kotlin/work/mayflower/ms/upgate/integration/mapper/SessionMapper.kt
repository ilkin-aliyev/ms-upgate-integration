package work.mayflower.ms.upgate.integration.mapper

import work.mayflower.ms.upgate.integration.model.SessionDto
import work.mayflower.ms.upgate.integration.model.SessionInfo

fun SessionDto.toApiResponse() = SessionInfo(createdAt, expiresAt, redirectUrl)