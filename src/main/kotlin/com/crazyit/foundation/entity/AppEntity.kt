package com.crazyit.foundation.entity

import javax.persistence.*

/**
 * 公共实体模型，提供公共字段，所有需要持久化的实体模型类的父类
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@MappedSuperclass
abstract class AppEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	open var id: Long = 0L

	fun isPersistent(): Boolean {
		return this.id != 0L
	}

	fun isNotPersistent(): Boolean {
		return !this.isPersistent()
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is AppEntity) return false

		if (id != other.id) return false

		return true
	}

	override fun hashCode(): Int {
		return id.hashCode()
	}
}