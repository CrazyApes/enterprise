package com.crazyit.core.exception

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
class InvalidDataException(
override var message: String,
override var notice: String
) : AppException(message = message, notice = notice)