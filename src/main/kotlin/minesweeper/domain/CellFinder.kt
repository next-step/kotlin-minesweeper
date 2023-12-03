package minesweeper.domain

class CellFinder(private val map: MutableMap<Position, Cell>) {
    constructor(initPositions: List<Position>) : this(initPositions.associateWith { Cell(it) }.toMutableMap())

    fun convert(minePosition: List<Position>) {
        minePosition.forEach {
            map[it] = Cell(it, true)
        }
    }

    fun find(position: Position): Cell {
        return map[position] ?: throw IllegalArgumentException("해당하는 위치가 존재하지 않습니다.")
    }

    companion object {
        fun from(initPosition: List<Position>): CellFinder {
            return CellFinder(initPosition.associateWith { Cell(it) }.toMutableMap())
        }
    }
}
