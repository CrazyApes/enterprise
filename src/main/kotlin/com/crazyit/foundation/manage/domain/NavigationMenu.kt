package com.crazyit.foundation.manage.domain

import com.crazyit.core.exception.InvalidDataException
import com.crazyit.foundation.AppEntity
import java.util.*
import javax.persistence.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
@Entity
@Table(name = "NAVIGATION_MENU")
class NavigationMenu(

	@Column(length = 10, nullable = false, updatable = false, unique = true)
	var title: String,

	@Column(length = 20, nullable = false)
	var actionType: String,

	@Column(length = 30, nullable = false)
	var url: String,

	@Column(nullable = false)
	var parentId: Long = 0L

): AppEntity() {

	@Transient
	var children: List<NavigationMenu> = ArrayList()

	@Column(length = 3, nullable = false)
	var sortId: Int = 1
	set(value) {
		if (value < 1 || value > 999) {
			throw InvalidDataException(
				message = "Navigation(sortId = $value)不符合规则 --> sortId 的值位于 1~999 之间",
				notice = "您输入了一个错误的排序号（排序号为1~999之间的正整数）")
		} else {
			this.sortId = value
		}
	}
}