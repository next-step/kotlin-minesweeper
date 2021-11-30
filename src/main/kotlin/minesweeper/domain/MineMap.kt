package minesweeper.domain

class MineMap(val mineMap: Map<Position, Boolean>) {

    companion object {

        fun from(positions: Positions, mineIndexes: List<Int>): MineMap {
            val minePositions = positions.slice(mineIndexes)
            val mineMap = positions.mapToIsIn(minePositions)
            return MineMap(mineMap)
        }
    }
}
