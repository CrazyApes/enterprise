package com.crazyit.foundation.price.provider.impl

import com.crazyit.core.app.AppProviderImpl
import com.crazyit.foundation.price.dao.PriceTemplateNodeRepo
import com.crazyit.foundation.price.domain.PriceTemplateNode
import com.crazyit.foundation.price.provider.PriceTemplateNodeProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
/**
 * Created by zhang on 2017/3/8.
 */
@Service
@Transactional
open class PriceTemplateNodeProviderImpl(
        @Autowired  var priceTemplateNodeRepo: PriceTemplateNodeRepo
) : AppProviderImpl<PriceTemplateNode>(priceTemplateNodeRepo), PriceTemplateNodeProvider {

}
