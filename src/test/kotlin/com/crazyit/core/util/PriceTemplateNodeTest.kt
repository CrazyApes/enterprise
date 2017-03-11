package com.crazyit.core.util

import com.crazyit.EnterpriseApplication
import com.crazyit.foundation.pricetemplateconfig.provider.PriceTemplateNodeProvider
import com.crazyit.service.pricetemplatenode.PriceTemplateNodeService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by zhang on 2017/3/9.
 */


@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(EnterpriseApplication::class))
@ActiveProfiles("dev")
 open class PriceTemplateNodeTest{
    @Autowired lateinit
    var priceTemplateNodeProvoder: PriceTemplateNodeProvider
    @Autowired lateinit
    var priceTemplateNodeService: PriceTemplateNodeService
    @Test
    fun testCreate(){
        priceTemplateNodeProvoder.createOne("节点一",2L,2,2,2)
    }
    @Test
    fun testCreateOne(){
        priceTemplateNodeProvoder.createOne("节点一",2L,2,2,2)
    }

}
