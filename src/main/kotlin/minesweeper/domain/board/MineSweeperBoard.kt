package minesweeper.domain.board

import minesweeper.domain.position.MineSweeperPositions

class MineSweeperBoard (
    private val board: List<MineSweeperPositions>,
    private val boardRange: BoardRange,
    mineQuantity: Int,
) : List<MineSweeperPositions> by board {

    init {
        require(mineQuantity <= boardRange.calculateArea()) { "지뢰 개수는 전체 크기를 초과할 수 없습니다." }
    }
}

