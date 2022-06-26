package view

interface IO {
    fun write(message: String)
    fun read(): String
}

object Console : IO {
    override fun write(message: String) {
        println(message)
    }

    override fun read(): String = readLine() ?: ""
}
