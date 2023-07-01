package domain.math

@JvmInline
value class PositiveNumber(val value: Int) {

    init {
        require(value > 0) {
            "value should be positive. your input: $value"
        }
    }
}
