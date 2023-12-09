package minesweeper.domain

class CellFinder(private val map: MutableMap<Position, Cell>) {
    private constructor(initPositions: List<Position>) : this(initPositions.associateWith { Cell(it) }.toMutableMap())

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
        return position.getAround()
            .mapNotNull { find(it) }
            .count {
                it.isMine
            }
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
