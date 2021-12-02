package minesweeper.domain

class MineMap(private val mineMap: Map<Position, Boolean>) {

    fun getBoards(): Board {
        return Board(mineMap.mapValues { (position, _) -> getCell(position) })
    }

    private fun getCell(position: Position): Cell {
        val isMine = mineMap.getValue(position)
        if (isMine) {
            return MineCell()
        }
        return getCellByAround(position)
    }

    private fun getCellByAround(position: Position): Cell {
        val around = position.around()
        val aroundCount = MineCount(around.count(::isMine))
        return BlockCell(aroundCount)
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
