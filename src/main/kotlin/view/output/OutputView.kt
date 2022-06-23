package view.output

import view.output.converter.OutputConverter

object OutputView {
    fun printlnOnlyMessage(message: String) {
        println(message)
    }

    fun <T> println(printable: T, outputConverter: OutputConverter<T>) {
        print(printable, outputConverter)
        println()
    }

    fun <T> print(printable: T, outputConverter: OutputConverter<T>) {
        println(outputConverter.convert(printable))
    }
}
