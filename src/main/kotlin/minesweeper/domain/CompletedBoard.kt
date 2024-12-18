package minesweeper.domain

sealed interface CompletedBoard : Board

class PlayerWonBoard(
    override val cells: Map<Coordinate, Cell>,
) : CompletedBoard {
    init {
        require(!cells.values.any { it is ClosedEmptyCell }) {
            "승리한 게임은 지뢰를 제외한 모든 칸을 열어야 합니다."
        }
    }
}

class MineDetonatedBoard(
    override val cells: Map<Coordinate, Cell>,
) : CompletedBoard {
    init {
        require(cells.values.any { it is DetonatedMineCell }) {
            "폭발한 지뢰가 있어야 합니다."
        }
    }
}
