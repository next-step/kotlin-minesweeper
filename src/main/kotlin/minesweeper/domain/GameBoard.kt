package minesweeper.domain

class GameBoard private constructor(
    val grid: List<List<Cell>>,
) {
    init {
        val rowLength = rowLength()
        require(rowLength > 0) {
            "행 길이는 1 이상이어야 합니다: rowLength = $rowLength"
        }

        val columnLength = columnLength()
        require(columnLength > 0) {
            "열 길이는 1 이상이어야 합니다: columnLength = $columnLength"
        }

        require(grid.all { it.size == columnLength }) {
            "게임판의 모든 행은 열 길이가 동일해야 합니다"
        }
    }

    fun rows(): List<Row> = grid.map { Row(it) }

    fun totalCellSize() = rowLength() * columnLength()

    private fun rowLength() = grid.size

    private fun columnLength() = grid.firstOrNull()?.size ?: 0

    companion object {
        fun of(
            rowLength: Int,
            columnLength: Int,
        ): GameBoard {
            val grid =
                List(rowLength) { row ->
                    List(columnLength) { column ->
                        BasicCell(row = row + 1, column = column + 1)
                    }
                }
            return GameBoard(grid)
        }

        fun from(grid: List<List<Cell>>): GameBoard {
            return GameBoard(grid)
        }
    }
}
