class GameBoard private constructor(
    private val grid: List<List<Cell>>,
    private val landmineFieldArchitect: LandmineFieldArchitect,
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

    fun plantMines(countOfMine: Int): GameBoard {
        require(countOfMine <= totalCellSize()) {
            "지뢰 개수는 전체 셀 개수보다 많을 수 없습니다: countOfMine=$countOfMine"
        }
        val newGrid = landmineFieldArchitect.design(this.grid, countOfMine)
        return GameBoard(newGrid, landmineFieldArchitect)
    }

    private fun totalCellSize() = rowLength() * columnLength()

    private fun rowLength() = grid.size

    private fun columnLength() = grid.firstOrNull()?.size ?: 0

    companion object {
        fun of(
            rowLength: Int,
            columnLength: Int,
            landmineFieldArchitect: LandmineFieldArchitect = LandmineFieldArchitect(),
        ): GameBoard {
            val grid =
                List(rowLength) { row ->
                    List(columnLength) { column ->
                        BasicCell(row = row + 1, column = column + 1)
                    }
                }
            return GameBoard(grid, landmineFieldArchitect)
        }

        fun from(
            grid: List<List<Cell>>,
            landmineFieldArchitect: LandmineFieldArchitect = LandmineFieldArchitect(),
        ): GameBoard {
            return GameBoard(grid, landmineFieldArchitect)
        }
    }
}
