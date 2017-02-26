package com.crazyit.foundation.service

import org.springframework.http.ResponseEntity

/**
 * @author CrazyApeDX
 * Created on 2017/2/25.
 */
interface NavigationMenuService : AppService {

	fun getNavigationTree(): ResponseEntity<String>

	fun getTopNavigationMenu(): ResponseEntity<String>

	fun getChildrenNavigationMenuByParentId(parentId: Long): ResponseEntity<String>
}