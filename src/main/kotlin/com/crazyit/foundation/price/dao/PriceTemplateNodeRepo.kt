package com.crazyit.foundation.price.dao

import com.crazyit.core.app.AppRepo
import com.crazyit.foundation.price.domain.PriceTemplateNode
import org.springframework.stereotype.Repository

/**
 * Created by Zack
 * on 2017/3/8.
 */
@Repository
interface PriceTemplateNodeRepo : AppRepo<PriceTemplateNode> {
    fun findByParentId(parentId:Long):List<PriceTemplateNode>
}