package domain

import constants.MineSweeperConstants.MINIMUM_MINE_COUNT

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value > MINIMUM_MINE_COUNT) { INVALID_MINE_COUNT }
    }

    operator fun rangeTo(other: Int): IntRange {
        return 0..(value - 1)
    }

    companion object {
        private const val INVALID_MINE_COUNT = "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
    }
}
