package minesweeper.domain

enum class ExceptionReason {
    NEGATIVE_POINT_VALUE,
    MINE_COUNT_OVER_BLOCKS,
    ILLEGAL_NEAR_MINE_RANGE
}
