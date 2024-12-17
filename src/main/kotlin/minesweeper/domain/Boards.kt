package minesweeper.domain

sealed interface Board {
    val cells: Map<Coordinate, Cell>
}

class PlayableBoard(
    override val cells: Map<Coordinate, Cell>,
) : Board {
    init {
        require(cells.isNotEmpty()) {
            "빈 판을 생성할 수 없습니다."
        }
    }

    fun get(
        y: Int,
        x: Int,
    ): Cell? = cells[Coordinate(y, x)]

    fun countMines(
        y: Int,
        x: Int,
    ): Int =
        Coordinate(y, x)
            .neighbors
            .count { cells[it] is MinedCell }
}

sealed interface CompletedBoard : Board

class PlayerWonBoard(
    override val cells: Map<Coordinate, Cell>,
) : CompletedBoard {
    init {
        require(!cells.values.any { it is UnopenedCell }) {
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
