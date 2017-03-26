package com.crazyit.core.app

import com.crazyit.core.constant.Global
import com.crazyit.core.exception.InvalidDataException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * 公共服务接口，提供公共服务方法，并实现公共分页方法
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
abstract class AppProviderImpl<Entity : AppEntity>(
	var repo: AppRepo<Entity>? = null
) : AppProvider<Entity> {

	fun initPage(page: Int, size: Int): Pageable {
		val usablePage: Int
		val usableSize: Int
		if (page < 1) usablePage = Global.DEFAULT_PAGEABLE_PAGE else usablePage = page
		if (size < 1) usableSize = Global.DEFAULT_PAGEABLE_SIZE else usableSize = size
		return PageRequest(usablePage - 1, usableSize)
	}

	fun initPage(page: Int, size: Int, sort: Sort): Pageable {
		val usablePage: Int
		val usableSize: Int
		if (page < 1) usablePage = Global.DEFAULT_PAGEABLE_PAGE else usablePage = page
		if (size < 1) usableSize = Global.DEFAULT_PAGEABLE_SIZE else usableSize = size
		return PageRequest(usablePage - 1, usableSize, sort)
	}

	fun initPage(page: Int, size: Int, direction: Sort.Direction, properties: String): Pageable {
		val usablePage: Int
		val usableSize: Int
		if (page < 1) usablePage = Global.DEFAULT_PAGEABLE_PAGE else usablePage = page
		if (size < 1) usableSize = Global.DEFAULT_PAGEABLE_SIZE else usableSize = size
		return PageRequest(usablePage - 1, usableSize, Sort(direction, properties))
	}

	override fun create(entity: Entity): Entity {
		if (0L != entity.id) throw InvalidDataException(
			message = "INSERT操作时，entity的id不为null，表示已经添加",
			notice = "该数据已经存在，请勿重新添加")
		else return this.repo!!.save(entity)
	}

	override fun remove(id: Long) = this.repo!!.delete(id)

	override fun modify(entity: Entity): Entity {
		if (0L == entity.id) throw InvalidDataException(
			message = "UPDATE，entity的id等于null，表示已经数据不存在",
			notice = "加载数据失败，请稍后重试或联系管理员")
		else return this.repo!!.save(entity)
	}

	override fun load(id: Long): Entity? = this.repo!!.findOne(id)

	override fun loadAll(): List<Entity> = this.repo!!.findAll()

	override fun loadAll(page: Int, size: Int): Page<Entity> = this.repo!!.findAll(this.initPage(page, size))
}