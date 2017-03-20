package com.crazyit.foundation.price.service.impl

import com.crazyit.core.app.AppProvider
import com.crazyit.core.constant.Global
import com.crazyit.core.exception.MismatchingDataException
import com.crazyit.foundation.price.NodeContent
import com.crazyit.foundation.price.domain.PriceTemplateNode
import com.crazyit.foundation.price.domain.ProductConfig
import com.crazyit.foundation.price.provider.PriceTemplateNodeProvider
import com.crazyit.foundation.price.provider.ProductConfigProvider
import com.crazyit.foundation.price.service.PriceTemplateService
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Zack
 *  Created  on 2017/3/10.
 */
@Service("priceTemplateService")
@Transactional
open class PriceTemplateServiceImpl(
        @Autowired var gson: Gson,
        @Autowired var priceTemplateNodeProvider: PriceTemplateNodeProvider,
        @Autowired var productConfigProvider: ProductConfigProvider
        ) : PriceTemplateService {
    override fun createWPDoor(templateId: Long, customerId: Long, basePricae: Long, baseSize: String, IncrementUnit: String, doorLeafIncrementPrice: Long, pricePerColor: Long,sleeveIncrementPrice:Long): ResponseEntity<String> {
        if (!ProductConfig.validateBaseSizePattern(baseSize)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("整套门的基础尺寸格式不正确！格式如（2100*900*300）")
        }
        productConfigProvider.create(ProductConfig(templateId=templateId,customerId = customerId,
                basePricae = basePricae,IncrementUnit = IncrementUnit,sleeveIncrementPrice = sleeveIncrementPrice ,doorLeafIncrementPrice = doorLeafIncrementPrice,pricePerColor = pricePerColor,baseSize = baseSize))
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    override fun findNodeContent(nodeType:String,templateId: Long): ResponseEntity<String> {
        val node = this.priceTemplateNodeProvider.load(templateId)?:
            throw MismatchingDataException(
                    message = "node(id = $templateId)数据不存在",
                    notice = "加载节点信息，请稍后重试或联系管理员"
            )
        if(Global.NODE_TYPE_PRODUCT.equals(nodeType)){
            val productConfig = this.productConfigProvider.findByTemplateId(templateId)?:
                    throw MismatchingDataException(
                    message = "productConfig(templateId = $templateId)数据不存在",
                    notice = "加载节点信息，请稍后重试或联系管理员"
            )
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(NodeContent( priceTemplateNode = node, productConfig = productConfig)))
        }
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(NodeContent( priceTemplateNode = node, productConfig = null)))

    }

    override fun createNode(title: String, customerId: Long, currentLevel: Int, nodeType: String, parentId: Long?): ResponseEntity<String> {
        val node :PriceTemplateNode = PriceTemplateNode(title = title, nodeType = nodeType,parentId = parentId,currentLevel = currentLevel,customerId= customerId)
        return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(priceTemplateNodeProvider.create(node)))
    }

}