package com.crazyit.foundation.app.provider

import com.crazyit.core.constant.Global
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * 公共服务接口，提供公共服务方法，并实现公共分页方法
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
interface AppProvider {

	fun initPage(page: Int, size: Int): Pageable {
		val usablePage: Int
		val usableSize: Int
		if (page < 0) usablePage = Global.DEFAULT_PAGEABLE_PAGE else usablePage = page
		if (size < 0) usableSize = Global.DEFAULT_PAGEABLE_SIZE else usableSize = size
		return PageRequest(usablePage, usableSize)
	}

	fun initPage(page: Int, size: Int, sort: Sort): Pageable {
		val usablePage: Int
		val usableSize: Int
		if (page < 0) usablePage = Global.DEFAULT_PAGEABLE_PAGE else usablePage = page
		if (size < 0) usableSize = Global.DEFAULT_PAGEABLE_SIZE else usableSize = size
		return PageRequest(usablePage, usableSize, sort)
	}

	fun initPage(page: Int, size: Int, direction: Sort.Direction, properties: String): Pageable {
		val usablePage: Int
		val usableSize: Int
		if (page < 0) usablePage = Global.DEFAULT_PAGEABLE_PAGE else usablePage = page
		if (size < 0) usableSize = Global.DEFAULT_PAGEABLE_SIZE else usableSize = size
		return PageRequest(usablePage, usableSize, Sort(direction, properties))
	}
}