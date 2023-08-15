// Variables
val firstName = "Joe"
val surName = "Soap"
val gender = "m"
val id = 6143
val grossSalary =67543.21
val paye = 38.5
val prsi = 5.2
val annualBonus = 1450.50
val cycleToWork = 54.33

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
        |______________________________________________________________________    
        |     PAYMENT DETAILS (gross pay: $grossSalary)                                                                    
        |______________________________________________________________________
        |           
        |           Bonus:  $annualBonus          
        |______________________________________________________________________
        |           PAYE: $paye                
        |           PRSI: $prsi  
        |           Cycle To Work: $cycleToWork         
        |______________________________________________________________________""".trimMargin("|")
    )
}