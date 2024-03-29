package com.kdpark0723.communityCommon.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class AppException(message: String) : RuntimeException(message)