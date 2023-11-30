package minesweeper.domain

class MineFinder(val map: Map<Position, Mine>) {

    fun convert(minePosition: List<Position>) {
        minePosition.forEach {
            map[it]?.isMine = true
        }
    }

    fun find(position: Position): Mine {
        return map[position] ?: throw IllegalArgumentException()
    }

    companion object {
        fun from(initPosition: List<Position>): MineFinder {
            return MineFinder(initPosition.associateWith { Mine(it) })
        }
    }

}
