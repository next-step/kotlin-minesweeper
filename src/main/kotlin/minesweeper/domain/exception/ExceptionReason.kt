package minesweeper.domain.exception

enum class ExceptionReason {
    ILLEGAL_POINT,
    NEGATIVE_POINT_VALUE,
    MINE_COUNT_OVER_BLOCKS,
    ILLEGAL_NEAR_MINE_RANGE
}
