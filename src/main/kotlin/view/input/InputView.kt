package view.input

abstract class InputView<T> {
    abstract val message: String
    abstract val value: T

    fun renderMessage() {
        println(message)
    }

    abstract fun readValue(): T
}
