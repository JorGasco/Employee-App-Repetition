package ie.setu.models


    data class Employee(
        val firstName: String, val surName: String, val gender: Char, var employeeID: Int,
        val grossSalary: Double, val payePercentage: Double, val prsiPercentage: Double,
        val annualBonus: Double, val cycleToWorkMonthlyDeduction: Double){


    }
