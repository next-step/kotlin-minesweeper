package view.input.converter

interface InputConverter<T> {
    fun convert(input: String): T
}
