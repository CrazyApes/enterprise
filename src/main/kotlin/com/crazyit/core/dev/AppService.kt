package com.crazyit.core.dev

import com.crazyit.core.constant.Global
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * 公共服务接口，提供公共服务方法，并实现公共分页方法
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
interface AppService<Entity: AppEntity> {

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

	fun create(entity: Entity): Entity
	fun remove(id: Long)
	fun modify(entity: Entity): Entity
	fun query(id: Long): Entity
	fun queryAll(): List<Entity>
	fun queryAll(page: Int, size: Int): Page<Entity>
	fun queryAll(page: Int, size: Int, sort: Sort): Page<Entity>
	fun queryAll(page: Int, size: Int, direction: Sort.Direction, properties: String): Page<Entity>
	fun countAll(): Long
}