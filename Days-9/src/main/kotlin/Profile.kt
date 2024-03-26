class Profile {

    var name: String = ""
    var age: Int = 0

    init {
        println("init call - ${this.name}")
    }
    constructor(){
        println("empty constructor call")

    }

    constructor(name:String){
        this.name = name
        println("name constructor call")

    }

    constructor(name:String, age:Int){
        this.name = name
        this.age = age
        println("age name, constructor call")
    }

    fun call(){
        println("${this.name} - ${this.age}")
    }
}