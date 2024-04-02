interface IProduct {

    var name : String
    fun addProduct(product: Product) : Product

    fun removeProduct(pid:Int) : Int

    // Static
    // Uygulamada hiçbir zaman ölmez
    companion object{
        var name : String? = "Serkan"
    }
}