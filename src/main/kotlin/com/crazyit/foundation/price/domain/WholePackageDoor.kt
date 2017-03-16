package com.crazyit.foundation.price.domain

import com.crazyit.core.app.AppEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by zhang on 2017/3/8.
 */

@Entity
@Table(name = "whole_package_door")
open class WholePackageDoor(
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
        //增量计算单位
        @Column(length = 10)
        var IncrementUnit: String,
        //门扇增量价格
        @Column(nullable = false)
        var doorLeafIncrementPrice: Long,
        //门套增量价格
        @Column(nullable = false)
        var doorCoverIncrementPrice: Long,
        //每增加一种颜色多少钱
        @Column(nullable = false)
        var PricePerColor:Long
) : AppEntity()