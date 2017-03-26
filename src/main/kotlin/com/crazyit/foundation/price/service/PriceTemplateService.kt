package com.crazyit.foundation.price.service

import com.crazyit.foundation.price.TreeNode
import org.springframework.http.ResponseEntity

/**
 * @author Zack
 *  Created on 2017/3/10.
 */

interface PriceTemplateService {
    fun createNode(title: String,customerId :Long,currentLevel :Int,nodeType: String,parentId: Long?): ResponseEntity<String>
    fun createPriceConfig(templateId:Long,customerId :Long,basePricae :Long,baseSize:String,IncrementUnit:String,doorLeafIncrementPrice:Long,pricePerColor:Long,sleeveIncrementPrice:Long):ResponseEntity<String>
    fun findNodeContent(nodeType: String,templateId: Long): ResponseEntity<String>
    fun getTreeNodes():ResponseEntity<String>
}