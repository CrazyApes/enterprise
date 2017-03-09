package com.crazyit.foundation.pricetemplateconfig.provider.impl

import com.crazyit.foundation.pricetemplateconfig.dao.PriceTemplateNodeRepo
import com.crazyit.foundation.pricetemplateconfig.domain.PriceTemplateNode
import com.crazyit.foundation.pricetemplateconfig.provider.PriceTemplateNodeProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
/**
 * Created by zhang on 2017/3/8.
 */
@Service
@Transactional
open class PriceTemplateNodeProviderImpl(
        @Autowired  val priceTemplateNodeRepo: PriceTemplateNodeRepo
) : PriceTemplateNodeProvider {
    override fun create(title: String,cusomerId :Long,currentLevlel :Int,kindCode: String,kindName: String,parentId: Long): PriceTemplateNode {
            return this.priceTemplateNodeRepo.save(PriceTemplateNode(title = title,
                    kindCode = kindCode,kindName = kindName,parentId = parentId,currentLevlel = currentLevlel,cusomerId= cusomerId));
    }
}
