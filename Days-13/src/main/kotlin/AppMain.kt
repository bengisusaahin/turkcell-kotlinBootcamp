fun main() {

    //val st = "AHMET".call(2)
    //println(st)
    //"x".call()

    val action = Action()
    action.userName("AHMET")
    val status = action.login("ali01", "12345")
    println(status)

    val customer = Customer(100, "Mehmet", "Bilsin")
    println(customer.hashCode())
    val customer2 = Customer(100, "Mehmet", "Bilsin")
    println(customer2.hashCode())
    customer2.cid = 101
    println(customer2.hashCode())

    customer2.cid = 100
    println(customer2.hashCode())


}

