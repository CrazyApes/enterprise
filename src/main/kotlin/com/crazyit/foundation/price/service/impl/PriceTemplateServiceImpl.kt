package com.crazyit.foundation.price.service.impl

import com.crazyit.foundation.price.provider.PriceTemplateNodeProvider
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
        @Autowired var priceTemplateNodeProvider: PriceTemplateNodeProvider
        ) : PriceTemplateService {
    override fun createOne(title: String, customerId: Long, currentLevel: Int, nodeType: Int, parentId: Long?): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(priceTemplateNodeProvider.createOne(title = title,
                nodeType = nodeType,parentId = parentId,currentLevel = currentLevel,customerId= customerId)))
    }

}