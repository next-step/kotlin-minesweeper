package minesweeper.domain

class MineFinder(private val map: Map<Position, Mine>) {
    constructor(initPositions: List<Position>) : this(initPositions.associateWith { Mine(it) })

    fun convert(minePosition: List<Position>) {
        minePosition.forEach {
            map[it]?.isMine = true
        }
    }

    fun find(position: Position): Mine {
        return map[position] ?: throw IllegalArgumentException("해당하는 위치가 존재하지 않습니다.")
    }

    companion object {
        fun from(initPosition: List<Position>): MineFinder {
            return MineFinder(initPosition.associateWith { Mine(it) })
        }
    }

}
