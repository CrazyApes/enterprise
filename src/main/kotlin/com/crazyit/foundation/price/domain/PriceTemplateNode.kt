package com.crazyit.foundation.price.domain


import com.crazyit.core.app.AppEntity
import com.crazyit.core.constant.Global
import com.crazyit.core.constant.enum.PriceTemplateNodeStatus
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created on 2017/3/5.
 * @author Zack
 */

@Entity
@Table(name = "price_template_node")
open class PriceTemplateNode(
        //价格配置模板的父id
        @Column(nullable = true)
        var parentId: Long?,
        //客户ID
        @Column(nullable = false)
        var customerId: Long,
        // 创建日期
        @Column(nullable = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        var createDate: Date = Date(),
        //当前层级
        @Column(nullable = false)
        var currentLevel :Int,
        // 用户状态： ACTIVE --> 启用； INACTIVE --> 注销
        @Column(length = 10, nullable = false)
        var status: PriceTemplateNodeStatus = PriceTemplateNodeStatus.ACTIVE,
        //名称
        @Column(length = 50, nullable = false)
        var title: String,
        //节点类型，产品种类，全套门，单面套，双面套，附加项
        @Column(nullable = false)
        var nodeType :Int

) :AppEntity(){

}