open class Base {

    var code = 0
    constructor(){
        println("Base call")
    }

    constructor(count:Int){
        println("Count call : $count")
    }

    open fun call(){
        action(0)
    }
    fun action(number: Int ) : Int{
        return number * number
    }
}