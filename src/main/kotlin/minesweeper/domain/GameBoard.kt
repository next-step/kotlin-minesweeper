package minesweeper.domain

class GameBoard private constructor(
    grid: List<List<Cell>>,
    val area: Area,
    val rows: Rows = Rows(grid.map { Row(it) }),
) {
    init {
        require(grid.all { it.size == area.width }) {
            "게임판의 모든 행은 열 길이가 동일해야 합니다"
        }
    }

    fun totalCellSize() = area.height * area.width

    fun find(location: Location): Cell? = rows.find(location)

    companion object {
        fun of(
            height: Int,
            width: Int,
        ): GameBoard {
            val area = Area(height = height, width = width)
            val grid = createFlatGrid(area).chunked(width)
            return GameBoard(grid, area)
        }

        fun from(grid: List<List<Cell>>): GameBoard {
            val area = Area(height = grid.size, width = grid.firstOrNull()?.size ?: 0)
            return GameBoard(grid, area)
        }

        private fun createFlatGrid(area: Area): List<Cell> {
            return (0 until area.height * area.width).map {
                BasicCell(
                    row = (it / area.width) + 1,
                    column = (it % area.width) + 1,
                )
            }
        }
    }
}
