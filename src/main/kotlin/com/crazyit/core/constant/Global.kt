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

	//产品类型
	val PRODUCT_TYPE_SINGLE_SLEEVE :String = "SINGLE_SLEEVE"
	val PRODUCT_TYPE_DOUBLE_SLEEVE :String = "DOUBLE_SLEEVE"
	val PRODUCT_TYPE_WHOLE_PACKAGE_DOOR:String = "WHOLE_PACKAGE_DOOR"

	//节点类型
	val NODE_TYPE_PRODUCT:String = "PRODUCT"
	val NODE_TYPE_CONFIG:String ="CONFIG"
	val NODE_TYPE_CUSTOMER:String = "CUSTOMER"

}