fun main() {
    // listof
    // immutable
    val iarr = listOf<String>("Ankara", "Ankara","İstanbul", "İzmir")

    // mutable
    val arr = mutableListOf<String>()
    val arr1 = ArrayList<String>()

    // add item
    arr.add("Ankara")
    arr.add("İstanbul")
    arr.add("İzmir")
    arr.add(1,"Bursa")

    //arr.remove("Ankara")
    arr.removeAll(listOf("Ankara"))
//    arr.removeAt(0)
//    arr.clear()

    val indexOf = arr.indexOf("Ankarax")
    if (indexOf > -1){
        arr.removeAt(indexOf)
        println("Delete successful")
    }

    arr.set(2, "Antalya")

    println("----------")
    for (item in arr){
        println(item)
    }

    println("----------")
    arr.forEach{
        println(it)
    }


    println(arr)

}