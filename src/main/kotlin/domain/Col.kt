package domain

import constants.MineSweeperConstants.MINIMUM_WIDTH

@JvmInline
value class Col(val value: Int) {
    init {
        require(value >= MINIMUM_WIDTH) { "$INVALID_COLUMN_SIZE value: $value" }
    }

    operator fun compareTo(other: Col): Int = value.compareTo(other.value)

    operator fun times(other: Row): Int = value * other.value

    companion object {
        private const val INVALID_COLUMN_SIZE = "너비는 1이상입니다."
    }
}
