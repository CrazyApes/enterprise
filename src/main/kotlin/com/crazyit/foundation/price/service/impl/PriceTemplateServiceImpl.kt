package com.crazyit.foundation.price.service.impl

import com.crazyit.core.app.AppProvider
import com.crazyit.foundation.price.NodeContent
import com.crazyit.foundation.price.domain.PriceTemplateNode
import com.crazyit.foundation.price.domain.WholePackageDoor
import com.crazyit.foundation.price.provider.PriceTemplateNodeProvider
import com.crazyit.foundation.price.provider.WholePackageDoorProvider
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
        @Autowired var nodeProvider: AppProvider<PriceTemplateNode>,
        @Autowired var wholePackageDoorProvider: WholePackageDoorProvider
        ) : PriceTemplateService {
    override fun createWPDoor(templateId: Long, customerId: Long, basePricae: Long, baseSize: String, IncrementUnit: String, doorLeafIncrementPrice: Long, pricePerColor: Long,sleeveIncrementPrice:Long): ResponseEntity<String> {
        if (!WholePackageDoor.validateBaseSizePattern(baseSize)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("整套门的基础尺寸格式不正确！格式如（2100*900*300）")
        }
        wholePackageDoorProvider.create(WholePackageDoor(templateId=templateId,customerId = customerId,
                basePricae = basePricae,IncrementUnit = IncrementUnit,sleeveIncrementPrice = sleeveIncrementPrice ,doorLeafIncrementPrice = doorLeafIncrementPrice,pricePerColor = pricePerColor,baseSize = baseSize))
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    override fun findNodeContent(nodeType: Int,templateId: Long): ResponseEntity<String> {
        var wholePackageDoor = this.wholePackageDoorProvider.findByTemplateId(templateId)
        //todo 还没获取sleeve和Pricetemplate
        var sleeve = null
        var priceTemplateNode = null
        if (wholePackageDoor==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("很遗憾...我们没有这条数据")
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(NodeContent(sleeve = sleeve, priceTemplateNode = priceTemplateNode, wholePackageDoor = wholePackageDoor)))
        }
    }

    override fun createNode(title: String, customerId: Long, currentLevel: Int, nodeType: Int, parentId: Long?): ResponseEntity<String> {
        val node :PriceTemplateNode = PriceTemplateNode(title = title, nodeType = nodeType,parentId = parentId,currentLevel = currentLevel,customerId= customerId)
        return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(nodeProvider.create(node)))
    }

}