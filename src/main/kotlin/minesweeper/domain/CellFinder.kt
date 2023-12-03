package minesweeper.domain

class CellFinder(private val map: Map<Position, Cell>) {
    constructor(initPositions: List<Position>) : this(initPositions.associateWith { Cell(it) })

    fun convert(minePosition: List<Position>) {
        minePosition.forEach {
            map[it]?.isMine = true
        }
    }

    fun find(position: Position): Cell {
        return map[position] ?: throw IllegalArgumentException("해당하는 위치가 존재하지 않습니다.")
    }

    companion object {
        fun from(initPosition: List<Position>): CellFinder {
            return CellFinder(initPosition.associateWith { Cell(it) })
        }
    }
}
