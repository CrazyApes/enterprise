package com.crazyit.service.pricetemplatenode

import org.springframework.http.ResponseEntity

/**
 * @author Zack
 *  Created on 2017/3/10.
 */

interface PriceTemplateNodeService{
    fun createOne(title: String,customerId :Long,currentLevlel :Int,nodeType: Int,parentId: Long?): ResponseEntity<String>
}