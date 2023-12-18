package minesweeper.domain.model

class Board private constructor(
    private val cells: List<List<Cell>>
) : List<List<Cell>> by cells {

    init {
        val flatCells = cells.flatten()

        require(!flatCells.all { cell -> cell.isMine() }) {
            "보드의 모든 셀이 지뢰여서는 안된다."
        }
    }

    companion object {
        fun create(width: Width, height: Height, mineCount: MineCount): Board {
            val mineCells = List(mineCount.toInt()) { Cell.MINE }
            val noneCells = List((width.toInt() * height.toInt()) - mineCount.toInt()) { Cell.NONE }
            val cells = (mineCells + noneCells)
                .shuffled()
                .chunked(width.toInt())

            return Board(cells)
        }
    }
}
