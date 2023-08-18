import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Employee
import mu.KotlinLogging



var employees = EmployeeAPI()

val logger = KotlinLogging.logger {}
fun main(args: Array<String>){
    logger.info { "Launching Employee App" }
start()
}
fun start(){
    var input : Int

    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> list()
            3 -> search()
            4 -> paySlip()
            5 -> dummyData()
            6 -> updateEmployee()

            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}
//private fun getTotalYearlyDeductions() = grossSalary * (payePercentage / 100) + grossSalary * (prsiPercentage / 100) + cycleToWorkMonthlyDeduction * 12

fun menu() : Int {
  print(""" 
         |Employee Menu
         |   1. Add Employee
         |   2. List All Employees
         |   3. Search Employees 
         |   4. Print Payslip for Employee
         |   5. add Data Employee
         |   6. Update
         |  -1. Exit
         |       
         |Enter Option : """.trimMargin())
    return readLine()!!.toInt()
}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employees.create(Employee(firstName, surname, gender, 0, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction))
}


fun list(){
    employees.findAll()
        .forEach{ println(it) }
}

fun search() {
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println(employee)
}

internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

internal fun getEmployeeToChangeDetails(): Employee? {
    print("Enter the employee id to change their details: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getPayslip())
}


fun updateEmployee() {

        val employee = getEmployeeToChangeDetails()

        if (employee != null) {
            print("Enter updated first name: ")
            val firstName = readLine().toString()
            print("Enter updated surname: ")
            val surname = readLine().toString()
            print("Enter updated gender (m/f): ")
            val gender = readLine()!!.toCharArray()[0]
            print("Enter updated gross salary: ")
            val grossSalary = readLine()!!.toDouble()
            print("Enter updated PAYE %: ")
            val payePercentage = readLine()!!.toDouble()
            print("Enter updated PRSI %: ")
            val prsiPercentage = readLine()!!.toDouble()
            print("Enter updated Annual Bonus: ")
            val annualBonus = readLine()!!.toDouble()
            print("Enter updated Cycle to Work Deduction: ")
            val cycleToWorkMonthlyDeduction = readLine()!!.toDouble()

            val updatedEmployee = Employee(
                firstName, surname, gender, employee.employeeID,
                grossSalary, payePercentage, prsiPercentage,
                annualBonus, cycleToWorkMonthlyDeduction
            )

            if (employees.update(employee.employeeID, updatedEmployee)) {
                println("Employee updated successfully.")
            } else {
                println("Failed to update employee. Employee not found.")
            }
        } else {
            println("Employee not found.")
        }
    }



fun dummyData() {
    employees.create(Employee("Joe", "Soap", 'm', 0, 35655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Joan", "Murphy", 'f', 0, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("Mary", "Quinn", 'f', 0, 75685.41, 40.0, 8.5, 4500.0, 0.0))
}


