package com.crazyit.core.app

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
abstract class AppException(
	override var message: String,
	open var notice: String
) : RuntimeException(message)