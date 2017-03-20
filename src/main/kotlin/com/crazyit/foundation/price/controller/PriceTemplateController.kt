package com.crazyit.foundation.price.controller


import com.crazyit.foundation.price.NodeContent
import com.crazyit.foundation.price.domain.PriceTemplateNode
import com.crazyit.foundation.price.domain.ProductConfig
import com.crazyit.foundation.price.service.PriceTemplateService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct

/**
 * @author zhang
 * Created on 2017/3/10.
 */

@Api(value = "PriceTemplate API", description = "价格模板相关API")
@RestController
@RequestMapping(value = "/templates", produces = arrayOf("application/json;charset=utf-8"))
open class PriceTemplateController(

        @Autowired
        var priceTemplateService: PriceTemplateService
) {
    @ApiOperation(value = "创建一个单独的价格模板节点", notes = "在代理商下面新建一个价格模板节点或者在产品系列西面新建一个节点",
            response = PriceTemplateNode::class)
    @ApiImplicitParams(
            ApiImplicitParam(name = "parentId", required = false, dataType = "Long",
                    value = "上一节点Id，根据当前节点自动获取"),
            ApiImplicitParam(name = "customerId", required = true, dataType = "Long",
                    value = "模板所属的代理商Id，根据前台自动指定"),
            ApiImplicitParam(name = "currentLevel", required = true, dataType = "Integer",
                    value = "节点层级，前端根据父节点level+1获得"),
            ApiImplicitParam(name = "title", required = true, dataType = "String",
                    value = "节点名称，用户自定义"),
            ApiImplicitParam(name = "nodeType", required = true, dataType = "String",
                    value = "节点类型，0为产品系列名称，1为全套门，2为"))
    @PostMapping(value = "/createNode" )
    fun createOneNode(parentId: Long?, customerId: Long,
                               currentLevel: Int, title: String, nodeType: String): ResponseEntity<String> {
        return this.priceTemplateService.createNode(title = title,
                nodeType = nodeType,parentId = parentId,currentLevel = currentLevel,customerId= customerId)
    }

    @ApiOperation(value = "创建整套门的价格参数配置", notes = "创建整套门的价格计算参数")
    @ApiImplicitParams(
            ApiImplicitParam(name = "templateId", required = true, dataType = "Long",
                    value = "所属节点的ID，在这个节点下面创建门的价格参数"),
            ApiImplicitParam(name = "customerId", required = true, dataType = "Long",
                    value = "价格所属的代理商Id"),
            ApiImplicitParam(name = "baseSize", required = true, dataType = "String",
                    value = "标准尺寸，不加价的尺寸一般情况下是2100*900*300"),
            ApiImplicitParam(name = "IncrementUnit", required = true, dataType = "String",
                    value = "增量计算单位，一般以厘米计额外的价格"),
            ApiImplicitParam(name = "sleeveIncrementPrice", required = true, dataType = "Long",
                    value = "门套的增量单价"),
            ApiImplicitParam(name = "doorLeafIncrementPrice", required = true, dataType = "Long",
                value = "门扇增量单价") ,
            ApiImplicitParam(name = "pricePerColor", required = true, dataType = "Long",
                    value = "每新增一种颜色加多少钱"),
            ApiImplicitParam(name = "basePricae", required = true, dataType = "Long",
            value = "一整套门的基础价格"))
    @PostMapping(value = "/createWPDoor" )
    fun cereateWPDoor(templateId: Long, customerId: Long, basePricae: Long, baseSize: String,
                      IncrementUnit: String, doorLeafIncrementPrice: Long, pricePerColor: Long,sleeveIncrementPrice:Long): ResponseEntity<String> {
        return this.priceTemplateService.createWPDoor(templateId=templateId,customerId = customerId,
                basePricae = basePricae,IncrementUnit = IncrementUnit,sleeveIncrementPrice = sleeveIncrementPrice ,doorLeafIncrementPrice = doorLeafIncrementPrice,pricePerColor = pricePerColor,baseSize = baseSize)
    }

    @ApiOperation(value = "获取节点里面的内容", notes = "创建整套门的价格计算参数",response = NodeContent::class)
    @ApiImplicitParams(
            ApiImplicitParam(name = "nodeType", required = true, dataType = "String",
                    value = "所属节点的ID，在这个节点下面创建门的价格参数"),
            ApiImplicitParam(name = "templateId", required = true, dataType = "Long",
                    value = "价格所属的代理商Id"))
    @GetMapping(value = "getNodeContent" )
    fun getNodeContent(@RequestParam("nodeType")nodeType: String,@RequestParam("templateId")templateId:Long): ResponseEntity<String>{
        return this.priceTemplateService.findNodeContent(nodeType = nodeType,templateId = templateId)
    }

    @ApiOperation(value = "获取价格配置树", notes = "初始化加载价格树",response = NodeContent::class)
    @GetMapping(value = "getConfigTree" )
    fun getNodeTree(){

    }
}
