fun main() {

    // Array -> Birden fazla değişken değerinin bir arada tutulduğu değişkenler
    val names = "Ali"
    // <String> -> generic type
//    val users = arrayOf<String>()
//    val usersx = users.plus("Mehmet")
//    println(usersx[0])
    var users = arrayOf<String>("Mehmet", "Ahmet", "Ali", "Zehra", "Ayşe")

    //index -> dizinin içindeki n.elemanı işaret eder ve "0"dan başlar.
    println(users[2])
    val data = users[0]

    // Dizi içerisindeki toplam eleman sayısı
    println(users.count())

    // Set -> dizi içindeki üyelerin değerlerinin değiştirilmesini sağlar.
    users.set(0, "Kemal")
    users[1] = "Ömer"
    println(users[0])
    println(users[1])

    println("------------")
    // loop
    for (item in users){
        println(item)
    }

    println("------------")
    val dataString = "Merhaba Kotlin"
    for (item in dataString){
        println(item)
    }

    println("------------")
    for (i in 0..users.size - 1 step 2){
    //for (i in 0..<users.size step 2){
        println(users[i])
    }

    if ("Ali" in users){
        println("Ali success")
    }

    println("------------iter1")
    //iterator
    val iter = users.iterator()
    iter.forEach {
        println(it)
    }

    println("------------iter2")
    iter.forEach {
        println(it)
    }

    // Benzersiz değerlere sahip olmak
    println("------------")
    var usersDis = arrayOf<String>("Mehmet", "Ahmet","Ahmet", "Ali", "Zehra", "Ayşe", "Ayşe")
    var newUSers = usersDis.distinct()
    for (item in newUSers){
        println(item)
    }
}