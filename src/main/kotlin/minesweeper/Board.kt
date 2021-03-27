package minesweeper

internal class Board private constructor(val cellRows: List<CellRow>) {

    companion object {
        internal fun createBoard(width: Int, height: Int, mineCount: Int, minePositions: (width: Int, height: Int, mineCount: Int) -> List<Position> = this::randomMinePositions): Board {
            val positions = minePositions(width, height, mineCount)
            val cellRows = mutableListOf<CellRow>()

            repeat(height) {
                val curY = it
                val cells = (0..width - 1).map {
                    val hasMine = positions.contains(Position(it, curY))
                    Cell(hasMine)
                }
                cellRows.add(CellRow(cells))
            }

            return Board(cellRows)
        }
        fun randomMinePositions(width: Int, height: Int, mineCount: Int): List<Position> {
            val range = width * height
            return (0..range).shuffled().take(mineCount).map {
                val x = it % height
                val y = it / height
                Position(x, y)
            }
        }
    }
}
