package com.crazyit.core.util

import com.crazyit.EnterpriseApplication
import com.crazyit.foundation.price.domain.PriceTemplateNode
import com.crazyit.foundation.price.provider.PriceTemplateNodeProvider
import com.crazyit.foundation.price.service.PriceTemplateService
import com.google.gson.Gson
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
    var priceTemplateService: PriceTemplateService
   @Autowired lateinit
      var gson: Gson
    @Test
    fun testCreate(){
//        priceTemplateNodeProvoder.create(PriceTemplateNode("节点一",2L,2,"2",2))
    }
    @Test
    fun testCreateOne(){
       for (i in 1..3) {
          priceTemplateService.createNode("系列" + i,i.toLong() , 2, "2", 1)
       }
    }
   @Test
   fun testTreeNodes(){
      val list = priceTemplateService.getTreeNodes();
      var json = gson.toJson(list)
   }

}
