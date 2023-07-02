package view.input

abstract class InputView<T, V> {
    abstract val message: String
    abstract val value: V

    fun renderMessage() {
        println(message)
    }

    abstract fun readValue(): T
}
