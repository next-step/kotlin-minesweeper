class GameBoard private constructor(private val grid: List<List<Cell>>) {
    init {
        val rowLength = grid.size
        require(rowLength > 0) {
            "행 길이는 1 이상이어야 합니다: rowLength = $rowLength"
        }

        val columnLength = grid.firstOrNull()?.size ?: 0
        require(columnLength > 0) {
            "열 길이는 1 이상이어야 합니다: columnLength = $columnLength"
        }

        require(grid.all { it.size == columnLength }) {
            "게임판의 모든 행은 열 길이가 동일해야 합니다"
        }
    }

    fun rows(): List<Row> = grid.map { Row(it) }

    companion object {
        fun of(
            rowLength: Int,
            columnLength: Int,
        ): GameBoard {
            val grid = List(rowLength) { List(columnLength) { BasicCell() } }
            return GameBoard(grid)
        }

        fun from(grid: List<List<Cell>>): GameBoard {
            return GameBoard(grid)
        }
    }
}
