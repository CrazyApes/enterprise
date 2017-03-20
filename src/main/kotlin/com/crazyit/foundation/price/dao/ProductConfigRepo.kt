package com.crazyit.foundation.price.dao

import com.crazyit.core.app.AppRepo
import com.crazyit.foundation.price.domain.ProductConfig
import org.springframework.stereotype.Repository

/**
 * @author  Zack
 * Created on 2017/3/19.
 */
@Repository
interface ProductConfigRepo :AppRepo<ProductConfig>{
    fun findByTemplateId(templateId:Long): ProductConfig
}
