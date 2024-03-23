fun main() {
    // Set
    // İçerisinde benzersiz object'ler saklamak için gerekli olan collection üyesidir.
    // içine add yapamadığımız set immutable read only
    val set1 = setOf<String>("Ali", "Veli", "Serkan", "Zehra", "Ali", "Ali")
    println(set1)
    val newSet = set1.toMutableSet()
    newSet.add("Ayşe")
    newSet.add("Selin")
    println(newSet)


    val set = mutableSetOf<String>("Ali", "Veli", "Serkan", "Zehra", "Ali", "Ali")
    set.add("Kemal")
    set.add("Serkan")
    set.add("Zehra")
    println(set)

    val c1 = Customer(100, "ALi")
    val c2 = Customer(101, "Serkan")
    val c3 = Customer(102, "Zehra")
    //val c4 = Customer(102, "Zehra")
    val c4 = Customer(103, "Zehra")

    println(c1.hashCode())
    println(c2.hashCode())
    println(c3.hashCode())
    println(c4.hashCode())

    val setCustomer = mutableSetOf<Customer>()
    setCustomer.add(c1)
    setCustomer.add(c1)
    setCustomer.add(c1)
    setCustomer.add(c2)
    setCustomer.add(c2)
    setCustomer.add(c2)
    setCustomer.add(c2)
    setCustomer.add(c3)
    setCustomer.add(c3)
    setCustomer.add(c3)
    setCustomer.add(c4)

    println(setCustomer)
}