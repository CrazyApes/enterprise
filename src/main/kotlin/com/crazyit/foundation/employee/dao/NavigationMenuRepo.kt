package com.crazyit.foundation.employee.dao

import com.crazyit.foundation.AppRepo
import com.crazyit.foundation.manage.domain.NavigationMenu
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
@Repository
interface NavigationMenuRepo : AppRepo<NavigationMenu> {

	fun findByParentId(parentId: Long): List<NavigationMenu>
}