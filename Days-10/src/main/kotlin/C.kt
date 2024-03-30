class C : Base {
    constructor(){
        code = 40
    }

    override fun call() {
        val end = action(30)
        println("C end: $end")
    }

    fun y(){
        println("y call")
    }
}