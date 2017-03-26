package com.crazyit.foundation.role.provider

import com.crazyit.EnterpriseApplication
import com.crazyit.foundation.role.query.RoleQuery
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/3/23.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(EnterpriseApplication::class))
@ActiveProfiles("dev")
@Transactional
@Rollback
class RoleProviderTest {

	@Autowired
	var roleProvider: RoleProvider? = null

	@Test
	fun createTest() {
		val title = "TEST01"
		val role = this.roleProvider!!.create(title)
		println(role)
		assert(0L != role.id)
		assert(role.title == title)
	}

	@Test
	fun removeTest() {
		val title = "TEST01"
		val role = this.roleProvider!!.create(title)
		val id = role.id
		println(id)
		this.roleProvider!!.remove(id)
		val queryRole = this.roleProvider!!.load(id)
		assert(null == queryRole)
	}

	@Test
	fun loadTest() {
		val title = "TEST01"
		val role = this.roleProvider!!.create(title)
		val id = role.id
		val queryRole = this.roleProvider!!.load(id)!!
		assert(id == queryRole.id)
	}

	@Test
	fun loadPageTest() {
		val title = "Test01"
		val role = this.roleProvider!!.create(title)
		val page = this.roleProvider!!.loadPage(RoleQuery(
			keywords = "Test"
		), 1, 10)
		print(page.content[0])
		assert(page.content.size > 0)
		assert(page.content[0].id == role.id)
	}

	@Test
	fun existsByTitleTest() {
		val title = "Test01"
		this.roleProvider!!.create(title)
		assert(this.roleProvider!!.existsByTitle(title))
	}
}