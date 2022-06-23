package view.output.converter

interface OutputConverter<T> {
    fun convert(printable: T): String
}
