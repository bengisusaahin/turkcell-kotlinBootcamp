fun main() {

    val pro1 = ProductImpl()
    val pro2 : IProduct = ProductImpl()
    val pro3 : IBasket = ProductImpl()

    pro2.name = "Süleyman"
    println(pro2.name)

    println(IProduct.name)
    IProduct.name = "Zehra"
    println(IProduct.name)
    IProduct.name = null

    val db = Using()
    db.call()



}