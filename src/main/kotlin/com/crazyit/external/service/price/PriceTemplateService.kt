package com.crazyit.external.service.price

import org.springframework.http.ResponseEntity

/**
 * @author Zack
 *  Created on 2017/3/10.
 */

interface PriceTemplateService {
    fun createOne(title: String,customerId :Long,currentLevel :Int,nodeType: Int,parentId: Long?): ResponseEntity<String>
}