import java.lang.Math.round


fun main(args: Array<String>){

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
        |Name: $firstName                                       ID: $id                  
        |Surname: $surName
        |Gender: $gender                                      
        |FullName: ${getFullName()}
        |______________________________________________________________________    
        |     PAYMENT DETAILS (gross pay: $grossSalary)                                                                    
        |______________________________________________________________________
        |           Salary: ${getMonthlySalary()}
        |           Bonus:  $annualBonus          
        |______________________________________________________________________
        |           Total Deductions : ${getTotalMonthlyDeductions()}
        |______________________________________________________________________
        |           PAYE: $payePercentage                
        |           PRSI: $prsiPercentage  
        |           Cycle To Work: $prsiPercentage         
        |______________________________________________________________________           
        |           Monthly Net Pay: ${getNetMonthlyPay()}    
        |           
        |______________________________________________________________________""".trimMargin("|")
    )
}

fun getFullName(): String{
    val fullName = "$firstName $surName"

    return when(gender){
        "m","M" -> "Mr. $fullName"
        "f","F" -> "Ms. $fullName"
        else -> fullName
    }
}

private fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
private fun getMonthlyBonus() = roundTwoDecimals(annualBonus / 12)
private fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
private fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
private fun getGrossMonthlyPay() = roundTwoDecimals((getMonthlySalary() + getMonthlyBonus()))
private fun getTotalMonthlyDeductions() = roundTwoDecimals(getMonthlyPAYE() + getMonthlyPRSI() + cycleToWorkMonthlyDeduction)
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

