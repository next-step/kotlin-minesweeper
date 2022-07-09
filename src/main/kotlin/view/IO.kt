package view

interface IO {
    fun write(message: String)
    fun read(): String
    fun writeLn(message: String = "") {
        write("$message\n")
    }
}

object Console : IO {
    override fun write(message: String) {
        print(message)
    }

    override fun read(): String = readln()
}
