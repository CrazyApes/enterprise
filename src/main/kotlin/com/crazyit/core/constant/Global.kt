package com.crazyit.core.constant

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
object Global {

	val DEFAULT_VISITOR_KEY = "ENTERPRICE_VISITOR"

	val DEFAULT_START_TIME: Date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1900-01-01 00:00:00")

	val DEFAULT_HEAD_IMAGE_URI: String = ""

	val DEFAULT_PAGEABLE_PAGE: Int = 0

	val DEFAULT_PAGEABLE_SIZE: Int = 10

	val DEFAULT_STANDARD_HEIGHT: Int = 0

	val DEFAULT_STANDARD_WIDTH: Int = 0

	val DEFAULT_STANDARD_DEPTH: Int = 0
}