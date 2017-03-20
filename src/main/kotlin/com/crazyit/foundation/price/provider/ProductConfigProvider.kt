
package com.crazyit.foundation.price.provider

import com.crazyit.core.app.AppProvider
import com.crazyit.foundation.price.domain.ProductConfig

/**
 * Created by Zack
 * on 2017/3/18.
 */

interface ProductConfigProvider : AppProvider<ProductConfig>{
    fun findByTemplateId(templateId:Long): ProductConfig?
}