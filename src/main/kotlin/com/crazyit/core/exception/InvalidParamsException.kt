package com.crazyit.core.exception

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
class InvalidParamsException(
	override var message: String,
    override var notice: String
) : AppException(message = message, notice = notice)