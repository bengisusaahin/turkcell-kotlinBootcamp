class Customer(val number: Int) : Employee() {

    override var tc: Long = 0
        // get() = this.tc // Recursive property accessor
        get() = tcNo()
        set(value) { field = value }

    override fun accountNumber(): Int {
        return number
    }

    fun tcNo(): Long {
        println("TC çalıştı")
        return 10
    }
}