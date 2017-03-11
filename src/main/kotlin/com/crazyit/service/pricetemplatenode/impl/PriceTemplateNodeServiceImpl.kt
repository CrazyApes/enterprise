package com.crazyit.service.pricetemplatenode.impl

import com.crazyit.foundation.employee.data.LoginEmployee
import com.crazyit.foundation.pricetemplateconfig.domain.PriceTemplateNode
import com.crazyit.foundation.pricetemplateconfig.provider.PriceTemplateNodeProvider
import com.crazyit.service.pricetemplatenode.PriceTemplateNodeService
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
@Service
@Transactional
open class PriceTemplateNodeServiceImpl(
        @Autowired var gson: Gson,
        @Autowired var priceTemplateNodeProvider: PriceTemplateNodeProvider
        ) : PriceTemplateNodeService {
    override fun createOne(title: String, customerId: Long, currentLevlel: Int, nodeType: Int, parentId: Long?): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(priceTemplateNodeProvider.createOne(title = title,
                nodeType = nodeType,parentId = parentId,currentLevlel = currentLevlel,customerId= customerId)))
    }

}