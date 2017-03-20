package com.crazyit.foundation.price

import com.crazyit.foundation.price.domain.PriceTemplateNode
import com.crazyit.foundation.price.domain.ProductConfig

/**
 * @author Zack
 * Created on 2017/3/19.
 * 用于展开每个节点时返回参数
 */
data class NodeContent(
        var priceTemplateNode: PriceTemplateNode,
        var productConfig: ProductConfig?
)