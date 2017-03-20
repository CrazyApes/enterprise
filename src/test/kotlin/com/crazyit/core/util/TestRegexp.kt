package com.crazyit.core.util

import com.crazyit.foundation.price.domain.ProductConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author Zack
 * created on 2017/3/19.
 */

@RunWith(SpringJUnit4ClassRunner::class)
open class TestRegexp{
    @Test
    fun testBaseSizePattern(){
        val baseSize = "2100*900*300"
        assert(ProductConfig.validateBaseSizePattern(baseSize))
    }
}
