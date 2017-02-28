package com.crazyit.service

import com.crazyit.foundation.AppService

/**
 * @author CrazyApeDX
 * Created on 2017/2/24.
 */
interface RoleService : AppService {

	fun existByTitle(title: String): Boolean
}