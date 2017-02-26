package com.crazyit.foundation.service.impl

import com.crazyit.foundation.repo.RoleRepo
import com.crazyit.foundation.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/2/24.
 */
@Service
@Transactional
open class RoleServiceImpl(
	@Autowired var roleRepo: RoleRepo
) : RoleService {

	override fun existByTitle(title: String): Boolean {
		return this.roleRepo.countByTitle(title) > 0
	}
}