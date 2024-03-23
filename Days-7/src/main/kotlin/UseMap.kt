fun main() {
    val map = mutableMapOf<String, Any>()

    // add item
    map.put("name", "Ali")
    map.put("name", "Süleyman")
    map.put("surname", "Bilsin")
    map.put("email", "süleyman@gmail.com")
    map.put("age", 39)
    map.put("status", true)

    // Get item
    println(map.get("name"))
    map.containsKey("name")
    map.get("email")?.let {
        println(it)
    }

    //Remove

    map.remove("statusx")?.let {
        println("Delete success: $it")
    }

    //Replace

    map.replace("statusx", false)?.let {
        println("Replace success: $it")
    }

    val newMap = mutableMapOf<String, Any>()
    newMap.put("class", 4)
    newMap.put("address", "İstanbul")
    map.putAll(newMap)

    // all keys
    val keys = map.keys
    val vals = map.values
    for (key in keys){
        println("key: $key val: ${map[key]}")
    }

    for (value in vals){
        println(value)
    }

    println("----------")
    map.forEach { (t, u) ->
        println("$t - $u")
    }

    println("----------")
    val entries = map.entries
    entries.forEach {
        println("${it.key} - ${it.value}" )
    }

    val default = map.getOrDefault("address", "Ankara")
    println(default)

    val filteredMap = map.filter {
        //it.key.contains("me")
        it.value is Int
    }
    println(filteredMap)
    println(map)

    val arrs = mutableListOf<MutableMap<String, Any>>()
    arrs.add(map)
}

