package com.crazyit.core.exception

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
abstract class AppException(
	override var message: String,
	open var notice: String
) : Throwable(message)