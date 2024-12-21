package minesweeper.domain

sealed interface CompletedBoard : Board

class PlayerWonBoard(
    override val cells: Cells,
) : CompletedBoard {
    init {
        require(!cells.isAnyEmptyCellClosed()) {
            "승리하기 위해서는 지뢰를 제외한 모든 칸을 열어야 합니다."
        }
    }
}

class MineDetonatedBoard(
    override val cells: Cells,
) : CompletedBoard {
    init {
        require(cells.isAnyMineDetonated()) {
            "폭발한 지뢰가 있어야 합니다."
        }
    }
}
