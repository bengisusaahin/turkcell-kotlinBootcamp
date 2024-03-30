class A : Base {

    constructor(): super(100){
        println("A call")
    }

    constructor(num : Int): super(num){
        println("A call - $num")
    }

    override fun call() {
        val end = action(10)
        println("A end: $end")
    }

    fun print(size:Int){
        if (size > 10){
            call()
        }else{
            super.call()
        }
    }
}