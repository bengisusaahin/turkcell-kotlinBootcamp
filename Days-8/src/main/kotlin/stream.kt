import java.util.stream.Stream
import kotlin.system.measureTimeMillis


fun main() {
//    val numbers = listOf(1, 2, 3, 4, 5)
//
//    // Tekrarlanamaz stream
//    val stream2 = numbers.stream()
//
//    // İlk tüketim
//    println("stream ve parallel stream 1 kere tüketilebilir")
//    stream2.forEach { num -> println(num) }
//
//    // Tekrar tüketim
//    try {
//        stream2.forEach { num -> println(num) }
//    } catch (e: IllegalStateException) {
//        println("2.kez hatası")
//        println(e.message)
//    }
//
//    println("-------------------------")
//    val list = mutableListOf<Int>()
//    repeat(100_000_000) {
//        list.add(it)
//    }
//
//    val measureTimeForStream = measureTimeMillis {
//        val result = list.stream()
//            .map { it * 2 }
//            .filter { it % 3 == 0 }
//            .toList()
//    }
//
//    val measureTimeForParallelStream = measureTimeMillis {
//        val result2 = list.parallelStream()
//            .map { it * 2 }
//            .filter { it % 3 == 0 }
//            .toList()
//    }
//
//    println(measureTimeForStream)
//    println(measureTimeForParallelStream)
//
//    val strList = mutableListOf<String>()
//
//    // Add element in list
//    strList.add("Geeks")
//    strList.add("for")
//    strList.add("Geeks")
//    val stream: Stream<String> = strList.stream().filter { it.contains("G") }
//    stream.forEach { str -> println(str) }
//
//    val strListSequence = mutableListOf<String>()
//
//// Liste içine öğe ekleme
//    strListSequence.add("Geeks")
//    strListSequence.add("for")
//    strListSequence.add("Geeks")
//
//// Sequence oluşturma
//    val sequence = strListSequence.asSequence().filter { it.contains("G") }
//
//// Sequence üzerinde işlem yapma
//    sequence.forEach { str -> println(str) }
    val rooms = listOf("Salon", "Mutfak", "Yatak Odası", "Banyo")

    // Ev temizliği işlemi Stream kullanılarak
    val streamTime = measureTimeMillis {
        println("Stream Temizlik:")
        rooms.forEach { room -> cleanRoom(room) }
    }
    println("Tek kişi ile temizlik tamamlandı - Geçen süre: $streamTime ms")


    // Paralel temizlik işlemi
    val parallelTime = measureTimeMillis {
        println("\nParalel Temizlik:")
        rooms.parallelStream().forEach { room -> cleanRoom(room) }
    }
    println("Çok kişi ile temizlik tamamlandı - Geçen süre: $parallelTime ms")
}


// Her bir odayı temizleyen metod
fun cleanRoom(room: String) {
    println("Temizlenen oda: $room, Thread: ${Thread.currentThread().name}")
    // Oda temizleme işleminin simülasyonu (örneğin, 1 saniye sürdüğünü varsayalım)
    Thread.sleep(1000)
}