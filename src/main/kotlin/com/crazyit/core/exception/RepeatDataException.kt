package com.crazyit.core.exception

import com.crazyit.core.app.AppException

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
class RepeatDataException(
	override var message: String,
    override var notice: String
) : AppException(message = message, notice = notice)