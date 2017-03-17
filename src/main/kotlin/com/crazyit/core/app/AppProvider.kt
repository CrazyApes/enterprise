package com.crazyit.core.app

import org.springframework.data.domain.Page

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
interface AppProvider<Entity : AppEntity> {

	fun create(entity: Entity): Entity
	fun remove(id: Long)
	fun modify(entity: Entity): Entity
	fun load(id: Long): Entity?
	fun loadAll(): List<Entity>
	fun loadAll(page: Int, size: Int): Page<Entity>
}