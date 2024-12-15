package minesweeper.domain

class GameBoard private constructor(
    val grid: List<List<Cell>>,
    val area: Area,
) {
    init {
        require(grid.all { it.size == area.width }) {
            "게임판의 모든 행은 열 길이가 동일해야 합니다"
        }
    }

    fun rows(): List<Row> = grid.map { Row(it) }

    fun totalCellSize() = area.height * area.width

    companion object {
        fun of(
            height: Int,
            width: Int,
        ): GameBoard {
            val area = Area(height = height, width = width)
            val grid =
                List(area.height) { row ->
                    List(area.width) { column ->
                        BasicCell(row = row + 1, column = column + 1)
                    }
                }
            return GameBoard(grid, area)
        }

        fun from(grid: List<List<Cell>>): GameBoard {
            val area = Area(height = grid.size, width = grid.firstOrNull()?.size ?: 0)
            return GameBoard(grid, area)
        }
    }
}
