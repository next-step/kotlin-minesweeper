package view.input.converter

object StringToIntConverter : InputConverter<Int> {
    override fun convert(input: String?): Int {
        return (input?.toIntOrNull() ?: 0).coerceAtLeast(0)
    }
}
