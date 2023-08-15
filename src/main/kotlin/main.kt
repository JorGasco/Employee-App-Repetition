import java.lang.Math.round

// Variables
val firstName = "Joe"
val surName = "Soap"
val gender = "m"
val id = 6143
val grossSalary =67543.21
val payePercentage = 38.5
val prsiPercentage = 5.2
val annualBonus = 1450.50
val cycleToWorkMonthlyDeduction = 54.33

fun main(args: Array<String>){
    printPayslip()
}

fun printPayslip(){
    println(
        """
        |______________________________________________________________________
        |Name: $firstName                                       ID: $id                  
        |Surname: $surName
        |Gender: $gender                                      
        |FullName: ${fullName()}
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

fun fullName(): String{
    val fullName = "$firstName $surName"

    return when(gender){
        "m","M" -> "Mr. $fullName"
        "f","F" -> "Ms. $fullName"
        else -> fullName
    }
}

private fun getMonthlySalary() = grossSalary / 12
private fun getMonthlyBonus() = annualBonus / 12
private fun getMonthlyPRSI() = getMonthlySalary() * (prsiPercentage / 100)
private fun getMonthlyPAYE() = getMonthlySalary() * (payePercentage / 100)
private fun getGrossMonthlyPay() = getMonthlySalary() + getMonthlyBonus()
private fun getTotalMonthlyDeductions() = getMonthlyPAYE() + getMonthlyPRSI() + cycleToWorkMonthlyDeduction
private fun getNetMonthlyPay() = getGrossMonthlyPay() - getTotalMonthlyDeductions()

//private fun getTotalYearlyDeductions() = grossSalary * (payePercentage / 100) + grossSalary * (prsiPercentage / 100) + cycleToWorkMonthlyDeduction * 12