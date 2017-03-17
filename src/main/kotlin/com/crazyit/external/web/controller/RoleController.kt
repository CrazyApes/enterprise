package com.crazyit.external.web.controller

import com.crazyit.core.constant.enum.OrderType
import com.crazyit.foundation.role.domain.Role
import com.crazyit.external.service.role.RoleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
@Api(value = "Role API", description = "员工角色相关API")
@RestController
@RequestMapping(value = "/roles", produces = arrayOf("application/json;charset=utf-8"))
open class RoleController(
	@Autowired var roleService: RoleService
) {

	@ApiOperation(value = "条件查询角色（分页）", notes = "根据查询条件查找符合条件的角色列表，并且分页",
		responseContainer = "Page" ,response = Role::class)
	@ApiImplicitParams(
		ApiImplicitParam(name = "keywords", required = false, dataType = "String",
			value = "关键词，会以模糊查询的方式去匹配角色的标题，可以不填写"),
		ApiImplicitParam(name = "orderType", required = false, dataType = "String",
			value = "排序规则，由于在服务端对应的类型是enum，所以只能输入ASC（正序）和DESC（倒序），默认为ASC"),
		ApiImplicitParam(name = "orderProperty", required = false, dataType = "String",
			value = "排序字段，可以不填写，默认是id"),
		ApiImplicitParam(name = "page", required = true, dataType = "Int",
			value = "分页页码，必须填写"),
		ApiImplicitParam(name = "size", required = true, dataType = "Int",
			value = "每页数据量，必须填写"))
	@GetMapping
	fun rolesGet(keywords: String?, orderType: OrderType?,
	             orderProperty: String?, page: Int, size: Int): ResponseEntity<String> {
		return this.roleService.loadPage(
			keywords = keywords,
			orderType = orderType,
			orderProperty = orderProperty,
			page = page,
			size = size
		)
	}

	@ApiOperation(value = "查询指定ID的角色信息", notes = "查询指定ID的角色的息", response = Role::class)
	@ApiImplicitParam(name = "id", required = true, dataType = "Long",
		value = "角色ID（任何正整数）")
	@GetMapping(value = "/{id}")
	fun rolesIdGet(@PathVariable("id") id: Long): ResponseEntity<String> {
		return this.roleService.load(id)
	}

	@ApiOperation(value = "新增角色", notes = "新增角色")
	@ApiImplicitParam(name = "title", required = true, dataType = "String",
		value = "角色的标题（格式为：位数为4~10位，只能输入中文）")
	@PostMapping
	fun rolesPost(title: String): ResponseEntity<String> {
		return this.roleService.create(title)
	}

	@ApiOperation(value = "删除角色", notes = "删除指定ID的角色")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", value = "角色ID")
	@DeleteMapping(value = "/{id}")
	fun rolesIdDelete(@PathVariable("id") id: Long): ResponseEntity<String> {
		return this.roleService.remove(id)
	}
}