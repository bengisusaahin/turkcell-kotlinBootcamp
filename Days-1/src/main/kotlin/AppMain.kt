fun main() {
    // One line comment
    /*
    * Multi line comment
    * */

    // Değişken oluşturma
    // var, val
    // var -> bir değişkenin değerinin daha sonra değiştirilmesi durumu
    // val -> sabit değere sahip bir değişken kurma

    // String type
    val userEmailOrUserName = "ali01"
    val name = "Süleyman "
    val surname = "Güner"
    val strAge = "30"
    val isFenerbahce:String = "true"
    var city = "İstanbul"
    //city = 100
    city = "Ankara"
    val concat = "Adı: $name Soyadı: $surname"

    println(userEmailOrUserName)
    println("Sn. $name")
    println("Count ${name.count()}")

    //ikisinin arasında maaliyet farkı yok
    println("Adı: $name Soyadı: $surname")
    println(concat)

    /*
    val nameAli = "Ali"
    sendString(nameAli)
    sendString("Ali")
     */

    //referans typedan kaynaklı tek değişken
    val namex ="Serkan"
    val namey ="Serkan"

    // Int -> Tam sayı
    // Orta seviye tam sayı
    val num1:Int = 2147000000

    // Yüksek seviye tam sayı
    val num2:Long = 999999999999999999

    // Düşük seviye tam sayı
    val num3:Short = 32000

    // Daha düşük seviye tam sayı
    val num4:Byte = 127

    // True - False
    // Karar kontrol gibi alanlarda kullanılır.
    var status1 = true

    // Ondalıklı değerler
    // Double -> Büyük ondalıklı değerler
    val num5 = 10.5
    // Float -> Orta seviye ondalıklı değerler
    val num6  = 10.5f

    // Any Type
    var obj:Any = true

    if (obj is String){

    }

    if (obj is Boolean){
        val status = obj.toString().toBoolean() //Gson mantigi
        println(obj)
        println(status)
    }

}