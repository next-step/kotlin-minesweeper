package minesweeper.domain

interface PositionSelector {
    fun select(mineMapMeta: MineMapMeta): Position
}
