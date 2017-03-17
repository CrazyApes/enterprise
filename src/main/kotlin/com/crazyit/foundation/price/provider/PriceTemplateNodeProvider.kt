package com.crazyit.foundation.price.provider

import com.crazyit.core.app.AppProvider
import com.crazyit.foundation.price.domain.PriceTemplateNode

/**
 * Created by zhang on 2017/3/8.
 */

interface PriceTemplateNodeProvider : AppProvider<PriceTemplateNode> {

    fun createOne(title: String,customerId :Long,currentLevel :Int,nodeType: Int,parentId: Long?): PriceTemplateNode
//    fun findByCustomerId
}
