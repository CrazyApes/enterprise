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

	//模板节点类型
	val NODE_TYPE_SERIES:Int = 0

	val NODE_TYPE_WHOLE_PACKAGE_DOOR:Int = 1

	val NODE_TYPE_SLEEVE:Int = 2
	//单面套双面套
	val SINGLE_SLEEVE:Int = 0
	val DOUBLE_SLEEVE:Int = 1

}