package com.crazyit.foundation.service

/**
 * @author CrazyApeDX
 * Created on 2017/2/24.
 */
interface RoleService : AppService {

	fun existByTitle(title: String): Boolean
}