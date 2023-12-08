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
            val cells = List(width.toInt() * height.toInt()) { index -> Cell.create(index < mineCount.toInt()) }
                .shuffled()
                .chunked(width.toInt())

            return Board(cells)
        }
    }
}
