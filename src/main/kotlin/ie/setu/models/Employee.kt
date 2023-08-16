package ie.setu.models

import java.io.Serializable


data class Employee(
        val firstName: String, val surName: String, val gender: Char, var employeeID: Int,
        val grossSalary: Double, val payePercentage: Double, val prsiPercentage: Double,
        val annualBonus: Double, val cycleToWorkMonthlyDeduction: Double){
        fun getFullName(): String{
            val fullName = "${firstName} ${surName}"

            return when(gender){
                'm', 'M' -> "Mr. $fullName"
                'f', 'F' -> "Ms. $fullName"
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

        fun getPayslip(){
            println(
                """
        |______________________________________________________________________
        |Fullname:${getFullName()}                                 ID: ${employeeID}                  
        |Gender: ${gender}                                      
        |______________________________________________________________________    
        |     PAYMENT DETAILS (gross pay: ${grossSalary}                                                                    
        |______________________________________________________________________
        |           Salary: ${getMonthlySalary()}
        |           Bonus:  ${annualBonus}          
        |______________________________________________________________________
        |           Total Deductions : ${getTotalMonthlyDeductions()}
        |______________________________________________________________________
        |           PAYE: ${payePercentage}                
        |           PRSI: ${prsiPercentage}  
        |           Cycle To Work: ${cycleToWorkMonthlyDeduction}         
        |______________________________________________________________________           
        |           Monthly Net Pay: ${getNetMonthlyPay()}    
        |           
        |______________________________________________________________________""".trimMargin("|")
            )
        }

    override fun toString(): String {
        return """ 
         |Employee ID: $employeeID
         |First Name = $firstName 
         |Surname = $surName  
         |Gender = $gender
         |grossSalary = $grossSalary 
         |payePercentage = $payePercentage 
         |prsiPercentage = $prsiPercentage 
         |annualBonus = $annualBonus 
         |cycleToWorkMonthlyDeduction = $cycleToWorkMonthlyDeduction     
         |""".trimMargin()

    }


}

