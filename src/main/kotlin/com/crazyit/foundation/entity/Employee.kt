package com.crazyit.foundation.entity

import com.crazyit.core.constant.Global
import com.crazyit.core.constant.enum.Sex
import com.crazyit.core.constant.enum.UserStatus
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@Entity
@Table(name = "USER")
open class Employee(

	// 用户姓名
	@Column(length = 10, nullable = false)
	var name: String,

	// 用户角色
	@ManyToOne(targetEntity = Role::class)
    @JoinColumn(name = "ROLE_ID")
    var role: Role

) : AppEntity() {

	// 用户头像图片路径
	@Column(length = 40, nullable = false)
	var headImageUri: String = Global.DEFAULT_HEAD_IMAGE_URI

	// 用户性别
	@Column(length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	var sex: Sex = Sex.SECRET

	// 用户生日
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	var birthday: Date = Global.DEFAULT_START_TIME

	// 用户状态： ACTIVE --> 在职； INACTIVE --> 闲置
	@Column(length = 10, nullable = false)
	var status: UserStatus = UserStatus.ACTIVE
}