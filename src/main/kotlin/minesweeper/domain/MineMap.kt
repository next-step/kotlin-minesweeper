package minesweeper.domain

class MineMap(private val mineMap: Map<Position, Boolean>) {

    fun getCells(): Map<Position, Cell> {
        return mineMap.mapValues { (position, isMine) ->
            getCell(position, isMine)
        }
    }

    private fun getCell(position: Position, isMine: Boolean): Cell {
        val aroundMineCount = position.around().count(::isMine)
        return CellFactory.getCell(isMine, MineCount(aroundMineCount))
    }

    private fun isMine(position: Position) = (mineMap[position] == true)

    companion object {

        fun from(positions: Positions, mineIndexes: List<Int>): MineMap {
            val minePositions = positions.slice(mineIndexes)
            val mineMap = positions.mapToIsIn(minePositions)
            return MineMap(mineMap)
        }
    }
}
