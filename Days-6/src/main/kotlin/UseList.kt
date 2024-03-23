fun main() {
    // listof
    // immutable
    val iarr = listOf<String>("Ankara", "Ankara","İstanbul", "İzmir")

    // mutable
    var arr = mutableListOf<String>()
    val arr1 = ArrayList<String>()

    // add item
    arr.add("Ankara")
    arr.add("İstanbul")
    arr.add("İstanbul")
    arr.add("İstanbul")
    arr.add("İzmir")
    arr.add(1,"Bursa")

    //arr.remove("Ankarax")
//    arr.removeAll(listOf("İstanbul"))
//    arr.removeAt(0)
//    arr.clear()

//    val indexOf = arr.indexOf("Ankara")
//    if (indexOf > -1){
//        arr.removeAt(indexOf)
//        println("Delete successful")
//    }
//
    arr.set(2, "Antalya")

    val datas = mutableListOf<String>()
    for (i in 0..9000){
        datas.add("Title - $i")
    }

    println("----------")
    val start = System.currentTimeMillis()
//    for (item in datas){
//        try {
//            Thread.sleep(1)
//        }catch (err:Error) {}
//        println(item)
//    }
//        datas.forEach {
//        try {
//           Thread.sleep(1)
//       }catch (err:Error) {}
//       println(it)
//    }
    val filteredDatas = datas.onEach {
        try {
            Thread.sleep(1)
        }catch (err:Error) {}
        println(it)
    }.groupBy { it.length }
    println(filteredDatas)
    val end = System.currentTimeMillis()
    val between = end - start
    println("Between: $between")

    println("----------")
    arr.forEach{
        println(it)
    }

    println("----------")
    arr.onEach {
        println(it)
    }

    //stringin referansına bakrak karışaltırma yapar yine
    val status1 = arr.contains("İstanbul")
    println(status1)
    if ("İstanbul" in arr){

    }

    arr.isEmpty()
    arr.size
    println("----------")
    for (i in 0..<arr.size){
        println(arr.get(i)) //get[i] de okey
    }

    // slice
    val newArr = arr.slice(0..2)
    println("new arr: $newArr")

    // filter aslında yeni bir dizi oluşturur
    val filterArr = arr.filter {
        it.count() > 6 && it.contains("b")
    }
    println(filterArr)

    val theList = listOf(10, 12, 30, 31, 40, 9, -3, 0)
    val mapResultList = theList.groupBy { it % 3 }
    println(mapResultList.get(0))
    println(mapResultList.get(1))

    val mapx = arr.groupBy { it.contains("z") } //true false a göre grupladı
    println(mapx.get(true))
    println(mapx.get(false))

    val arrx = arr.map { "$it-TR" }
    println(arrx)

    println(arr)

}