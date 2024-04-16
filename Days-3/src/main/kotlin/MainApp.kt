import kotlin.random.Random

fun main(){
    val num1 = 10.0
    val num2 = 15.0
    var end = 0.0

    // sum
    end = (num1 + num2)
    println("sum: $end")

    // subtraction
    end = num1 - num2
    println("subtraction: $end")

    // division
    end = num1 / num2
    var st = end.toString()
    st = st.substring(0,4)
    println("division: $st")
    println("division: ${Math.round(end)}") //yakın neyse ona yuvarlama
    println("division: ${Math.ceil(end)}") //yukarı yuvarlama
    println("division: ${Math.floor(end)}") //asagi yuvarlama

    //multiplication
    end = num1 * num2
    println("multiplication: $end")

    //mod
    end = num1 % num2
    println("mod: $end")

    // Relational Operators
    var status = false

    //big
    status = num1 > num2
    println("big: $status")

    //small
    status = num1 < num2
    println("small: $status")

    //big or small
    status = num1 >= num2
    println("bigOrEq: $status")

    //small or big
    status = num1 <= num2
    println("smallOrEq: $status")

    //equal
    status = num1 == num2
    println("equal: $status")

    //not equal
    status = num1 != num2
    println("Not equal: $status")

    val v1:Any = num1
    val v2:Any = num2
    println("v1 hashcode: ${v1.hashCode()}" )
    println("v2 hashcode: ${v2.hashCode()}" )
    status = v1===v2
    println("Any kıyaslama: $status")

    // atama ve işlem yapma
    var n1 = 11
    var n2 = 12

    // +=
    n1 += n2 // n1 = n1 + n2
    println("+= $n1")

    // -=
    n1 -= n2 // n1 = n1 - n2
    println("-= $n1")

    // *=
    n1 *= n2 // n1 = n1 * n2
    println("*= $n1")

    // /=
    n1 /= n2 // n1 = n1 / n2
    println("/= $n1")

    // %=
    n1 %= n2 // n1 = n1 % n2
    println("%= $n1")

    println("n1= $n1")
    println("n2= $n2")

    // Arttırma ve eksiltme
    // ++
    ++n1 // n1 = n1 + 1
    n1++ // önce yazdır sonra arttır
    println(n1++)
    println(n1)

//    if (n1++ > 14){
//        println("14 ten büyüktür")
//    }else{
//        println("14 ten küçüktür")
//        println(n1)
//    }

    if (++n1 > 14){
        println("14 ten büyüktür")
    }else{
        println("14 ten küçüktür")
        println(n1)
    }

    if (!(n1 > 14)){
        println("koşul sağlanıyor")
    }else{
        println("koşul sağlanmıyor")
    }

    // logic operatorler
    val a = 10
    val b = 20
    val c = 30

    // && -> And
    //soldaki koşul ile sağdaki koşulun sağlanması
    status = b > 25 && c > 25
    println("&&: $status")

    val obj:Random? = null
    if (obj != null && obj.nextInt(100) > 50) { }

    // || -> veya
    status = a > 15 || b > c || c > 25
    println("|| : $status ")

    // iç içe kullanım
    status = (a > 5 || c > 40) && b < 9
    println("iç içe : $status ")

}