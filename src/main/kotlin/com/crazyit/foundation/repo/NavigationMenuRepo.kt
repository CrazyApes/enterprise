package com.crazyit.foundation.repo

import com.crazyit.foundation.entity.NavigationMenu
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
@Repository
interface NavigationMenuRepo : AppRepo<NavigationMenu> {

	fun findByParentId(parentId: Long): List<NavigationMenu>
}