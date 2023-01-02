package minesweeper.domain

enum class CellOpenResult {
    MINE_FOUND, MINE_NOT_FOUND, SPREAD_NEEDED;

    fun noNearMine() = this == SPREAD_NEEDED
    fun isNotMine() = this != MINE_FOUND
}
