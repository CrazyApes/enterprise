package com.crazyit.foundation.repo

import com.crazyit.foundation.entity.Employee
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Repository
interface EmployeeRepo : AppRepo<Employee>