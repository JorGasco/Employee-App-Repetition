package ie.setu.controllers

import ie.setu.logger
import ie.setu.models.Employee
import java.io.*



var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class EmployeeAPI {


    private var employees = ArrayList<Employee>()

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeID == id }
    }

    fun delete(id: Int): Boolean {
        val foundEmployee = findOne(id)

        if (foundEmployee != null) {
            // Use the 'remove' function to remove the found employee from the list.
            employees.remove(foundEmployee)
            return true
        }
        return false
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

    fun loadEmployeesFromFile(): Boolean {
        // ? https://stackoverflow.com/questions/57758314/store-custom-kotlin-data-class-to-disk
        logger.debug {"Loading employees from file"}

        try {
            ObjectInputStream(FileInputStream("employees.xml")).use { it ->
                @Suppress("UNCHECKED_CAST") // https://stackoverflow.com/questions/64041447/in-kotlin-is-there-a-safe-way-to-do-objectinputstream-readobject
                employees = it.readObject() as ArrayList<Employee>
                lastId = employees.maxWith(Comparator.comparingInt {it.employeeID}).employeeID + 1

                logger.debug {"Loaded ${employees.size} employees from file"}
                return true
            }
        } catch (e: FileNotFoundException) {
            logger.debug {"No employees file found"}
        } catch (e: Exception) {
            logger.debug {"Error reading employees file: ${e.message}"}
        }

        return false
    }

    fun saveEmployeesToFile(): Boolean {
        // ? https://stackoverflow.com/questions/57758314/store-custom-kotlin-data-class-to-disk
        logger.debug {"Saving employees to file"}

        try {
            ObjectOutputStream(FileOutputStream("employees.xml")).use {
                it.writeObject(employees)

                logger.debug {"Saved ${employees.size} employees to file"}
                return true
            }
        } catch (e: Exception) {
            logger.debug {"Error saving employees file: ${e.message}"}
        }

        return false
    }

}



