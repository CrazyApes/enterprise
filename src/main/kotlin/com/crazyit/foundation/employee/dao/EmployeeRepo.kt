package com.crazyit.foundation.employee.dao

import com.crazyit.foundation.AppRepo
import com.crazyit.foundation.employee.domain.Employee
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Repository
interface EmployeeRepo : AppRepo<Employee>