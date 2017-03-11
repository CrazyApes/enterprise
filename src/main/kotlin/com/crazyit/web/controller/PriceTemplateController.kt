package com.crazyit.web.controller


import com.crazyit.foundation.pricetemplateconfig.domain.PriceTemplateNode
import com.crazyit.foundation.pricetemplateconfig.provider.PriceTemplateNodeProvider
import com.crazyit.service.pricetemplatenode.PriceTemplateNodeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author zhang
 * Created on 2017/3/10.
 */

@Api(value = "PriceTemplate API", description = "价格模板相关API")
@RestController
@RequestMapping(value = "/templates", produces = arrayOf("application/json;charset=utf-8"))
open class PriceTemplateController(
        @Autowired var priceTemplateNodeService: PriceTemplateNodeService
) {
    @ApiOperation(value = "创建一个单独的价格模板节点", notes = "在代理商下面新建一个价格模板节点或者在产品系列西面新建一个节点",
            response = PriceTemplateNode::class)
    @ApiImplicitParams(
            ApiImplicitParam(name = "parentId", required = false, dataType = "Long",
                    value = "上一节点Id，根据当前节点自动获取"),
            ApiImplicitParam(name = "customerId", required = true, dataType = "Long",
                    value = "模板所属的代理商Id，根据前台自动指定"),
            ApiImplicitParam(name = "currentLevlel", required = true, dataType = "Int",
                    value = "节点层级，前端根据父节点level+1获得"),
            ApiImplicitParam(name = "title", required = true, dataType = "String",
                    value = "节点名称，用户自定义"),
            ApiImplicitParam(name = "nodeType", required = true, dataType = "Int",
                    value = "节点类型，0为产品系列名称，1为全套门，2为"))
    @PostMapping
    fun createOneNode(parentId: Long?, customerId: Long,
                               currentLevlel: Int, title: String, nodeType: Int): ResponseEntity<String> {

        return this.priceTemplateNodeService.createOne(title = title,
                nodeType = nodeType,parentId = parentId,currentLevlel = currentLevlel,customerId= customerId)
    }
}
