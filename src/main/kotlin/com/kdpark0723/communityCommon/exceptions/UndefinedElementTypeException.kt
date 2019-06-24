package com.kdpark0723.communityCommon.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
class UndefinedElementTypeException(message: String = "Error: This type isn't defined.") : RuntimeException(message)