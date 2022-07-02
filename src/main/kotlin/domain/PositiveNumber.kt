package domain

@JvmInline
value class PositiveNumber(
    private val number: Int
) {
    constructor(value: String) : this(requireNotNull(value.toIntOrNull()) { VALUE_IS_NOT_INT_ERROR_MSG })

    init {
        require(number > 0) { VALUE_IS_NOT_POSITIVE_NUMBER_ERROR_MSG }
    }

    fun toInt() = number

    operator fun compareTo(anotherNumber: Any): Int =
        when (anotherNumber) {
            is PositiveNumber -> number.compareTo(anotherNumber.number)
            is Int -> number.compareTo(anotherNumber)
            else -> throw IllegalArgumentException()
        }

    operator fun times(anotherNumber: Any): Int =
        when (anotherNumber) {
            is PositiveNumber -> number.times(anotherNumber.number)
            is Int -> number.times(anotherNumber)
            else -> throw IllegalArgumentException()
        }

    companion object {
        const val VALUE_IS_NOT_INT_ERROR_MSG = "정수가 아닙니다."
        const val VALUE_IS_NOT_POSITIVE_NUMBER_ERROR_MSG = "자연수가 아닙니다."
    }
}
