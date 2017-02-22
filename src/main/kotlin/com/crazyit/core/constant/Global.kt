package com.crazyit.core.constant

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
object Global {

	val DEFAULT_START_TIME: Date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1900-01-01 00:00:00")

	val DEFAULT_HEAD_IMAGE_URI: String = ""

	val DEFAULT_PAGEABLE_PAGE: Int = 0

	val DEFAULT_PAGEABLE_SIZE: Int = 10
}