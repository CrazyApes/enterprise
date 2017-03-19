package com.crazyit.foundation.price.service

import org.springframework.http.ResponseEntity

/**
 * @author Zack
 *  Created on 2017/3/10.
 */

interface PriceTemplateService {
    fun createNode(title: String,customerId :Long,currentLevel :Int,nodeType: Int,parentId: Long?): ResponseEntity<String>
    fun createWPDoor(templateId:Long,customerId :Long,basePricae :Long,baseSize:String,IncrementUnit:String,doorLeafIncrementPrice:Long,pricePerColor:Long,sleeveIncrementPrice:Long):ResponseEntity<String>
    fun findNodeContent(nodeType: Int,templateId: Long): ResponseEntity<String>
}