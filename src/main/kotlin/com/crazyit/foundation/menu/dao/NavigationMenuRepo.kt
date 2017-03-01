package com.crazyit.foundation.menu.dao

import com.crazyit.foundation.app.dao.AppRepo
import com.crazyit.foundation.menu.domain.NavigationMenu
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
@Repository
interface NavigationMenuRepo : AppRepo<NavigationMenu> {

	fun findByParentId(parentId: Long): List<NavigationMenu>
}