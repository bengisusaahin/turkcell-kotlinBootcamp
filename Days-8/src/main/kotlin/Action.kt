class Action{
    var name = "Süleyman"
    var surname = "Güner"

    // Functions
    // fun
    fun noParams(){
        println("no params call")
    }

    fun params(userID : Int, token: String){
        println("$userID - $token")
    }

    fun count(name:String, surname:String) : Int {
        val sum = name + surname
        return sum.count()
    }

    fun fnc1(name:String, age:Int?) : Int{
        var count = name.count()
        age?.let {
            count += it
        }
        return count
    }

    fun fnc1(){
        println("fnc1 call")
    }

    fun order(a:Double, b:Double, fncFunction: (Double,Double) -> Double) : Double{
        return fncFunction(a,b)
    }
}