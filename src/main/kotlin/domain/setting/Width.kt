package domain.setting

@JvmInline
value class Width(val value: Int) {
    init {
        require(value in 1..MAX_VALUE) { "height must be not less than 1 but not more than ${Height.MAX_VALUE}" }
    }

    companion object {
        const val MAX_VALUE = 500
    }
}
