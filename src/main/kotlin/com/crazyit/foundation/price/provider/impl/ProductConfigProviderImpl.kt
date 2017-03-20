package com.crazyit.foundation.price.provider.impl

import com.crazyit.core.app.AppProviderImpl
import com.crazyit.foundation.price.dao.ProductConfigRepo
import com.crazyit.foundation.price.domain.ProductConfig
import com.crazyit.foundation.price.provider.ProductConfigProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Zack
 * on 2017/3/18.
 */
@Service
@Transactional
open class ProductConfigProviderImpl(
       @Autowired var productConfigRepo: ProductConfigRepo
) : AppProviderImpl<ProductConfig>(productConfigRepo), ProductConfigProvider {
    override fun findByTemplateId(templateId: Long): ProductConfig {
        return this.productConfigRepo.findByTemplateId(templateId)
    }
}

