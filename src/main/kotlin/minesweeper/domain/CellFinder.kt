package minesweeper.domain

class CellFinder(private val map: MutableMap<Position, Cell>) {
    private constructor(initPositions: List<Position>) : this(initPositions.associateWith { Cell(it) }.toMutableMap())

    private val list = listOf(
        Position(-1, -1), Position(-1, 0), Position(-1, 1), Position(0, -1), Position(0, 1), Position(1, -1), Position(1, 0), Position(1, 1)
    )

    fun convert(minePosition: List<Position>) {
        minePosition.forEach {
            map[it] = Cell(it, true)
        }
    }

    fun find(position: Position): Cell? {
        return map[position]
    }

    fun getAroundMinesCount(cell: Cell): Int {
        val position = cell.position
        return list.mapNotNull {
            val nextPosition = position + it
            find(nextPosition)
        }.count { it.isMine }
    }

    companion object {
        fun init(height: Size, width: Size): CellFinder {
            return CellFinder(
                height.getNumbers()
                    .flatMap { rowNum ->
                        width.getRows(rowNum)
                    }.toList()
            )
        }
    }
}
