fun main() {
    // nesne üretimi
    // obj -> object, nesne
    // Action -> class
    // Action() -> kurucu method / constructor
    val obj:Action = Action()
    val obj2:Any = Action() // böyle yapınca özelliklerini kullanamıyoruz bu şekilde tanımlamaya gerek yok
    //val obj3:String = Action()
    val obj4 = obj2 as Action
    obj4.name


    obj.name = "Bengisu"
    println(obj.name)
    println(obj.surname)

    val obj1 = Action()
    println(obj1.name)

    // run function
    val no = obj.noParams()
    obj.noParams()
    obj.noParams()

    obj.params(200, "dfsdvgrwev")

    val size = obj.count("Ali", "Bilmem")
    println("Size: $size")
    if (obj.count("Süleyman","Güner") > 10){
        println("line code")
    }

    obj.fnc1("Ahmet", null)
    obj.fnc1()

    fun senOrder(x: Double, y:Double) = x + y
    val sm = obj.order(10.0, 40.0, ::senOrder)
    println(sm)

    fun senOrderMinus(x: Double, y:Double) = x - y
    val sminus = obj.order(10.0, 40.0, ::senOrder)
    println(sm)


}