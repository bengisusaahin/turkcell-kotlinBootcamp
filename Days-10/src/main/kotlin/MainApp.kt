fun main(){

    val a = A(25)
    val b = B()
    val c = C()

//    a.call()
//    b.call()
//    c.call()

    poliCall(a)
    poliCall(b)
    poliCall(c)
}

fun poliCall(base: Base){
    if (base is A){
        base.print(12)
    }
    base.call()
}