package com.crazyit.foundation.employee.domain

import com.crazyit.core.app.AppEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
@Entity
@Table(name = "PERMISSION")
open class Permission(
) : AppEntity()