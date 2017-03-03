package com.crazyit.foundation.employee.domain

import com.crazyit.core.constant.Global
import com.crazyit.core.constant.enum.Sex
import com.crazyit.core.constant.enum.EmployeeStatus
import com.crazyit.core.app.AppEntity
import com.crazyit.foundation.role.domain.Role
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@Entity
@Table(name = "EMPLOYEE")
open class Employee(

	// 用户姓名
	@Column(length = 5, nullable = false)
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
	var status: EmployeeStatus = EmployeeStatus.ACTIVE

	companion object {

		/**
		 * 验证客户端键入的用户名是否符合格式的方法
		 * 格式：位数为10~20位，必须包含大写字母，此外还可以输入小写字母和数字
		 * @param username 客户端键入的
		 */
		fun validateUsernamePattern(username: String): Boolean {
			if (!username.matches(Regex("^[a-zA-Z0-9]{10,20}$"))) return false
			else return username.matches(Regex("[A-Z]+"))
		}

		/**
		 * 验证客户端键入的密码是否符合格式的方法
		 * 格式：位数为8~16位，必须包含字母和数字，此外还可以输入~、#、_、.
		 */
		fun validatePasswordPattern(password: String): Boolean {
			if (!password.matches(Regex("^[a-zA-Z0-9~#_.]{8,16}$"))) return false
			else if (!password.matches(Regex("[a-zA-Z]+"))) return false
			else return password.matches(Regex("[0-9]+"))
		}

		/**
		 * 验证客户端输入的姓名是否符合格式的方法
		 * 格式：位数为2~5位，只能输入中文
		 */
		fun validateNamePattern(name: String): Boolean {
			return name.matches(Regex("^[\\u4e00-\\u9fa5]{2,5}$"))
		}
	}
}