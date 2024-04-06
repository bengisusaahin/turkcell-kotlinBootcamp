// Statikliği kendi içinde kuruyor
fun String.call(size: Int): String {
    //println("String call, $this")
    return this.lowercase().substring(0, size)
}

fun Action.login(username:String, password: String) : Boolean {
    if (username == "ali01" && password == "12345")
        return true
    return false
}