package com.crazyit.core.exception

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
class IsUsedDataException(
	override var message: String,
    override var notice: String
) : AppException(message = message, notice = notice)