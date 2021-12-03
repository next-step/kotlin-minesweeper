package minesweeper.domain

class MineMap(private val mineMap: Map<Position, Boolean>) {

    fun getBoard(): Board {
        val board = mineMap.mapValues { (position, isMine) ->
            getCell(position, isMine)
        }
        return Board(board)
    }

    private fun getCell(position: Position, isMine: Boolean): Cell {
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
