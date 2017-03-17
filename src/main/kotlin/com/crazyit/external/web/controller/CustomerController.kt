package com.crazyit.external.web.controller

import com.crazyit.core.constant.enum.OrderType
import com.crazyit.external.service.customer.CustomerService
import com.crazyit.foundation.customer.domain.Customer
import com.crazyit.foundation.employee.domain.Employee
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
@Api(value = "Customer API", description = "客户（经销商）相关API")
@RestController
@RequestMapping(value = "/customers", produces = arrayOf("application/json;charset=utf-8"))
open class CustomerController(
	@Autowired var customerService: CustomerService
) {

	@ApiOperation(value = "查询指定ID的客户（经销商）信息", notes = "查询指定ID的客户（经销商）信息",
		response = Customer::class)
	@ApiImplicitParam(name = "id", required = true, dataType = "Long",
		value = "客户（经销商）ID（任何正整数）")
	@GetMapping(value = "/{id}")
	fun customersIdGet(@PathVariable("id") id: Long): ResponseEntity<String> {
		return this.customerService.loadOne(id)
	}

	@ApiOperation(value = "条件查询客户（经销商）（分页）", notes = "根据查询条件查询符合条件的客户（经销商）列表，并且分页",
		responseContainer = "Page", response = Employee::class)
	@ApiImplicitParams(
		ApiImplicitParam(name = "keywords", required = false, dataType = "String",
			value = "关键词，会以模糊查询的方式去匹配客户（经销商）的姓名、电话、地址、传真，可以不填写"),
		ApiImplicitParam(name = "orderType", required = false, dataType = "String",
			value = "排序规则，由于在服务端对应的类型是enum，所以只能输入ASC（正序）和DESC（倒序），默认为ASC"),
		ApiImplicitParam(name = "orderProperty", required = false, dataType = "String",
			value = "排序字段，可以不填写，默认是id"),
		ApiImplicitParam(name = "page", required = true, dataType = "Int",
			value = "分页页码，必须填写"),
		ApiImplicitParam(name = "size", required = true, dataType = "Int",
			value = "每页数据量，必须填写"))
	@GetMapping
	fun customersGet(keywords: String?, orderType: OrderType?,
	                 orderProperty: String?, page: Int, size: Int): ResponseEntity<String> {
		return this.customerService.loadPage(keywords, orderType, orderProperty, page, size)
	}

	@ApiOperation(value = "新增客户（经销商）", notes = "提供姓名、手机号码、地址和传真来快速新增客户（经销商）")
	@ApiImplicitParams(
		ApiImplicitParam(name = "name", required = true, dataType = "String",
			value = "姓名（格式为：位数为2~5位，只能输入中文）"),
		ApiImplicitParam(name = "mobile", required = true, dataType = "String",
			value = "电话号码（格式为：以1开头的11位数字）"),
		ApiImplicitParam(name = "address", required = true, dataType = "String",
			value = "地址，长度不超过80位"),
		ApiImplicitParam(name = "fax", required = false, dataType = "String",
			value = "传真号码，长度不超过15位，可以不填写"))
	@PostMapping
	fun customersPost(name: String, mobile: String, address: String, fax: String?): ResponseEntity<String> {
		return this.customerService.create(name, mobile, address, fax)
	}
}