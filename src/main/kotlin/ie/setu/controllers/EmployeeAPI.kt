package ie.setu.controllers

import ie.setu.models.Employee


var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class EmployeeAPI {


    private val employees = ArrayList<Employee>()

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeID == id }
    }

    fun create(employee: Employee) {
        employee.employeeID = getId()
        employees.add(employee)
    }

    fun update(id: Int, updatedEmployee: Employee): Boolean {
        val foundEmployee = findOne(id)

        if (foundEmployee != null) {
            foundEmployee.apply {
                firstName = updatedEmployee.firstName
                surName = updatedEmployee.surName
                gender = updatedEmployee.gender
                grossSalary = updatedEmployee.grossSalary
                payePercentage = updatedEmployee.payePercentage
                prsiPercentage = updatedEmployee.prsiPercentage
                annualBonus = updatedEmployee.annualBonus
                cycleToWorkMonthlyDeduction = updatedEmployee.cycleToWorkMonthlyDeduction
            }
            return true
        }
        return false
    }




}



