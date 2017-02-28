package com.crazyit.foundation

import com.crazyit.foundation.AppEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

/**
 * 公共DAO接口，该接口或许会提供公共的自定义的JPA方法
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@NoRepositoryBean
interface AppRepo<Entity: AppEntity> : JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity>