import ie.setu.models.Employee

var employee =  Employee("Joe", "Soap", 'm', 6143, 67543.21, 38.5, 5.2, 1450.50, 54.33)
fun main(args: Array<String>){
    add()
    var input : Int

    do {
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPRSI()}")
            3 ->println("Monthly PAYE: ${getMonthlyPAYE()}")
            4 -> println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalMonthlyDeductions()}")
            6 -> println("Monthly Net Pay: ${getNetMonthlyPay()}")
            7 -> println(getPayslip())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun getPayslip(){
    println(
        """
        |______________________________________________________________________
        |Name: ${employee.firstName}                                       ID: ${employee.employeeID}                  
        |Surname: ${employee.surName}
        |Gender: ${employee.gender}                                      
        |FullName: ${getFullName()}
        |______________________________________________________________________    
        |     PAYMENT DETAILS (gross pay: ${employee.grossSalary}                                                                    
        |______________________________________________________________________
        |           Salary: ${getMonthlySalary()}
        |           Bonus:  ${employee.annualBonus}          
        |______________________________________________________________________
        |           Total Deductions : ${getTotalMonthlyDeductions()}
        |______________________________________________________________________
        |           PAYE: ${employee.payePercentage}                
        |           PRSI: ${employee.prsiPercentage}  
        |           Cycle To Work: ${employee.cycleToWorkMonthlyDeduction}         
        |______________________________________________________________________           
        |           Monthly Net Pay: ${getNetMonthlyPay()}    
        |           
        |______________________________________________________________________""".trimMargin("|")
    )
}

fun getFullName(): String{
    val fullName = "${employee.firstName} ${employee.surName}"

    return when(employee.gender){
        'm', 'M' -> "Mr. $fullName"
        'f', 'F' -> "Ms. $fullName"
        else -> fullName
    }
}

private fun getMonthlySalary() = roundTwoDecimals(employee.grossSalary / 12)
private fun getMonthlyBonus() = roundTwoDecimals(employee.annualBonus / 12)
private fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (employee.prsiPercentage / 100))
private fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (employee.payePercentage / 100))
private fun getGrossMonthlyPay() = roundTwoDecimals((getMonthlySalary() + getMonthlyBonus()))
private fun getTotalMonthlyDeductions() = roundTwoDecimals(getMonthlyPAYE() + getMonthlyPRSI() + employee.cycleToWorkMonthlyDeduction)
private fun getNetMonthlyPay() = roundTwoDecimals((getGrossMonthlyPay() - getTotalMonthlyDeductions()))

fun roundTwoDecimals(number: Double) = "%.2f".format(number).toDouble()

//private fun getTotalYearlyDeductions() = grossSalary * (payePercentage / 100) + grossSalary * (prsiPercentage / 100) + cycleToWorkMonthlyDeduction * 12

fun menu() : Int {
    print("""
         Employee Menu for ${getFullName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          -1. Exit
         Enter Option : """)
    return readLine()!!.toInt()
}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter employee ID: ")
    val employeeID = readLine()!!.toInt()
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

    employee = Employee(firstName, surname, gender, employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction)
}

