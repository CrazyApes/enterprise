package com.crazyit.foundation.role.domain

import com.crazyit.core.app.AppEntity
import javax.persistence.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@Entity
@Table(name = "ROLE")
open class Role(

	// 角色名
	@Column(length = 10, nullable = false, updatable = false, unique = true)
	var title: String

//	@OneToMany(fetch = FetchType.LAZY)
//	var permissionList: List<Permission> = ArrayList<Permission>()

) : AppEntity() {

	companion object {

		/**
		 * 验证客户端输入的标题是否符合格式的方法
		 * 格式：位数为4~10位，只能输入中文
		 */
		fun validateTitlePattern(title: String): Boolean {
			return title.matches(Regex("^[\\u4e00-\\u9fa5]{4,10}$"))
		}
	}
}