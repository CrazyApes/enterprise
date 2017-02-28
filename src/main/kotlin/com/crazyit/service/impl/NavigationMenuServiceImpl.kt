package com.crazyit.service.impl

import com.crazyit.foundation.manage.domain.NavigationMenu
import com.crazyit.foundation.employee.dao.NavigationMenuRepo
import com.crazyit.service.NavigationMenuService
import com.google.gson.Gson
import org.apache.commons.collections.CollectionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
@Service
@Transactional
open class NavigationMenuServiceImpl(
	@Autowired var gson: Gson,
    @Autowired var navigationMenuRepo: NavigationMenuRepo
) : NavigationMenuService {

	override fun getNavigationTree(): ResponseEntity<String> {
		val topMenuList: List<NavigationMenu> = this.navigationMenuRepo.findByParentId(0L)
		this.loadChildrenToTheLast(topMenuList)
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(topMenuList))
	}

	private fun loadChildrenToTheLast(menuList: List<NavigationMenu>) {
		if (CollectionUtils.isNotEmpty(menuList)) {
			for (menu: NavigationMenu in menuList) {
				val children = this.navigationMenuRepo.findByParentId(menu.id)
				menu.children = children
				this.loadChildrenToTheLast(children)
			}
		}
	}

	override fun getTopNavigationMenu(): ResponseEntity<String> {
		val topMenuList: List<NavigationMenu> = this.navigationMenuRepo.findByParentId(0L)
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(topMenuList))
	}

	override fun getChildrenNavigationMenuByParentId(parentId: Long): ResponseEntity<String> {
		val childrenMenuList: List<NavigationMenu> = this.navigationMenuRepo.findByParentId(parentId)
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(childrenMenuList))
	}
}