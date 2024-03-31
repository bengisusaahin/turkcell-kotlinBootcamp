abstract class Employee {

    abstract  var tc: Long
    abstract fun accountNumber() : Int

    fun accountName(): String {
        val cId = accountNumber()
        var name = ""
        if (cId == 100){
            name = "Ali Bilmem"
        }else if (cId == 200){
            name = "Zehra Bilirim"
        }
        return name

    }

    fun accountTotal() : Int {
        val cId = accountNumber()
        var total = 0
        if (cId == 100){
            total = 1000000
        }else if (cId == 200){
            total = 20000000
        }
        return total
    }
}