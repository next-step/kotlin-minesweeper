package minesweeper.domain

internal class Board private constructor(val cellRows: List<CellRow>) {

    companion object {
        internal fun createBoard(boardSpec: BoardSpec, minePositions: (BoardSpec) -> List<Position> = this::randomMinePositions): Board {
            val positions = minePositions(boardSpec)
            val cellRows = mutableListOf<CellRow>()

            repeat(boardSpec.height.value) {
                val curY = it
                val cells = (0 until boardSpec.width.value).map {
                    val hasMine = positions.contains(Position(NaturalNumber(it), NaturalNumber(curY)))
                    Cell(hasMine)
                }
                cellRows.add(CellRow(cells))
            }

            return Board(cellRows)
        }
        fun randomMinePositions(boardSpec: BoardSpec): List<Position> {
            val range = boardSpec.width * boardSpec.height

            return (0..range.value).shuffled().take(boardSpec.mineCount.value).map {
                val x = it % boardSpec.height.value
                val y = it / boardSpec.height.value
                Position(NaturalNumber(x), NaturalNumber(y))
            }
        }
    }
}
