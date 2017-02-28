package com.crazyit.foundation.employee.domain

import com.crazyit.foundation.AppEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

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

) : AppEntity()