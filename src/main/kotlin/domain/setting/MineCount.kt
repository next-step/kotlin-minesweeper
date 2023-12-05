package domain.setting

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value in 1..Height.MAX_VALUE * Width.MAX_VALUE) { "height must be not less than 1 but not more than $MAX_VALUE" }
    }

    companion object {
        const val MAX_VALUE = 500
    }
}
