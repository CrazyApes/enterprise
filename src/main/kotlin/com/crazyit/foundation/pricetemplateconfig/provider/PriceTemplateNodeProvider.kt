package com.crazyit.foundation.pricetemplateconfig.provider

import com.crazyit.core.app.AppProvider
import com.crazyit.foundation.pricetemplateconfig.domain.PriceTemplateNode

/**
 * Created by zhang on 2017/3/8.
 */

interface PriceTemplateNodeProvider : AppProvider {
    fun create(title: String,cusomerId :Long,currentLevlel :Int,kindCode: String,kindName: String,parentId: Long): PriceTemplateNode
}