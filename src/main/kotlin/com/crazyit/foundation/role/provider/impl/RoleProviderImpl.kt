package com.crazyit.foundation.role.provider.impl

import com.crazyit.core.app.AppProviderImpl
import com.crazyit.core.exception.InvalidDataException
import com.crazyit.core.exception.IsUsedDataException
import com.crazyit.core.exception.MismatchingDataException
import com.crazyit.core.exception.RepeatDataException
import com.crazyit.foundation.employee.dao.EmployeeRepo
import com.crazyit.foundation.role.dao.RoleRepo
import com.crazyit.foundation.role.domain.Role
import com.crazyit.foundation.role.provider.RoleProvider
import com.crazyit.foundation.role.query.RoleQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
@Service
@Transactional
open class RoleProviderImpl @Autowired constructor(
	var roleRepo: RoleRepo,
	var employeeRepo: EmployeeRepo
): AppProviderImpl<Role>(roleRepo), RoleProvider {

	/**
	 * 新增角色的方法
	 * @param title 客户端输入的角色标题
	 * @return 新增角色实体模型对象
	 * @throws InvalidDataException 客户端输入的标题不符合格式要求（位数4~10位，只能填写中文）时抛出
	 * @throws RepeatDataException 客户端输入的标题在数据库中已经存在时抛出
	 */
	override fun create(title: String): Role {
		if (title.length > 10) {
			throw InvalidDataException(
				message = "Role(title = $title) 长度超过10位",
				notice = "角色标题不能超过10位"
			)
		} else if (this.existsByTitle(title)) {
			throw RepeatDataException(
				message = "Role(title = $title)数据已经存在",
				notice = "标题 $title 已经存在，请更换标题后重试"
			)
		} else {
			return this.roleRepo.save(Role(title = title))
		}
	}

	/**
	 * 删除指定id的角色的方法
	 * @param id 被删除的角色的id
	 * @throws MismatchingDataException 指定id的角色数据不存在时抛出
	 * @throws IsUsedDataException 还有员工在使用被删除的角色时抛出
	 */
	override fun remove(id: Long) {
		val role = this.roleRepo.findOne(id)
		if (null == role) {
			throw MismatchingDataException(
				message = "Role(id = $id)数据不存在",
				notice = "数据不存在，请稍后重试或联系管理员"
			)
		} else {
			val count = this.employeeRepo.countByRole(role)
			if (count > 0L) {
				throw IsUsedDataException(
					message = "存在role = $role 的Employee数据，不能删除id = $id 的Role",
					notice = "该角色还有员工在使用，删除失败"
				)
			} else {
				this.roleRepo.delete(id)
			}
		}
	}


	override fun modify(title: String): Role {
		if (title.length > 10) {
			throw InvalidDataException(
				message = "Role(title = $title) 长度超过10位",
				notice = "角色标题不能超过10位"
			)
		} else if (this.existsByTitle(title)) {
			throw RepeatDataException(
				message = "Role(title = $title)数据已经存在",
				notice = "标题 $title 已经存在，请更换标题后重试"
			)
		} else {
			return this.roleRepo.save(Role(title = title))
		}
	}

	/**
	 * 加载指定id的角色的方法
	 * @param id 客户端键入的id
	 * @return 指定id的角色实体模型数据
	 */
	override fun load(id: Long): Role? {
		return this.roleRepo.findOne(id)
	}

	override fun loadPage(query: RoleQuery, page: Int, size: Int): Page<Role> {
		return this.roleRepo.findAll(query.getCondition(), this.initPage(page, size))
	}

	/**
	 * 验证指定标题的角色数据是否存在的方法
	 * @param title 客户端键入的标题
	 * @return 验证结果，如果指定标题的数据存在则返回true，反之则是false
	 */
	override fun existsByTitle(title: String): Boolean {
		return this.roleRepo.countByTitle(title) > 0L
	}
}