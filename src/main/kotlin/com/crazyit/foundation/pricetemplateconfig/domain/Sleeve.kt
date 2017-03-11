package com.crazyit.foundation.pricetemplateconfig.domain

import com.crazyit.core.app.AppEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by zhang on 2017/3/10.
 * 门套model
 */

@Entity
@Table(name = "whole_package_door")
open class Sleeve(
        //所属模板节点Id
        @Column(nullable = false)
        var templateId :Long,
        //客户Id
        @Column(nullable = false)
        var customerId: Long,
        //基础价格
        @Column(nullable = false)
        var basePricae: Long,
        //基础尺寸
        @Column(length = 50)
        var baseSize: String,
        //增量计价格方式
        @Column(length = 10)
        var IncrementType: String,
        //门扇增量价格
        @Column(nullable = false)
        var IncrementPrice: Long,
        //每增加一种颜色多少钱
        @Column(nullable = false)
        var PricePerColor:Long
) : AppEntity()