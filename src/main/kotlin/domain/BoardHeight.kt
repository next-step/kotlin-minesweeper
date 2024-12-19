package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT

@JvmInline
value class BoardHeight(val value: Int) {
    init {
        require(value >= MINIMUM_HEIGHT) { "$INVALID_ROW_SIZE value: $value" }
    }

    operator fun compareTo(other: Int): Int = value.compareTo(other)

    operator fun times(other: BoardWidth): Int = value * other.value

    companion object {
        private const val INVALID_ROW_SIZE = "높이는 1이상입니다."
    }
}
