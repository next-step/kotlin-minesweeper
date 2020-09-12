import model.MineCount
import model.MinePlate
import model.Position

class MinesweeperGame() {
    fun start(minePlate: MinePlate, mineCount: MineCount): MinePlate {
        val mineList = positionList(minePlate).shuffled().take(mineCount.value.value)

        mineList.forEach { minePlate.value[it.y].column.blocks[it.x].changeToMine() }

        return setBlockValue(minePlate)
    }

    private fun setBlockValue(minePlate: MinePlate): MinePlate {
        val rowSize = minePlate.rowSize()
        val colSize = minePlate.colSize()

        for (y in 0 until rowSize) {
            for (x in 0 until colSize) {
                val mineCount = calculateMineCount(x, y, minePlate)
                minePlate.setMineValue(x, y, mineCount)
            }
        }

        return minePlate
    }

    private fun calculateMineCount(x: Int, y: Int, minePlate: MinePlate): Int {
        var mineCount = 0
        val startXPosition = x - 1
        val endXPosition = x + 1
        val startYPosition = y - 1
        val endYPosition = y + 1

        for (dy in startYPosition..endYPosition) {
            for (dx in startXPosition..endXPosition) {
                try {
                    if(minePlate.block(dx, dy) == minePlate.block(x, y)) {
                        continue
                    }
                    if (minePlate.block(dx, dy).isMine()) {
                        mineCount++
                    }
                } catch (ex: IndexOutOfBoundsException) {
                    continue
                }
            }
        }
        return mineCount;
    }


    private fun positionList(minePlate: MinePlate): List<Position> {
        val positionList: MutableList<Position> = mutableListOf()
        for (j in minePlate.rowIndices()) {
            for (i in minePlate.colIndices(j)) {
                positionList.add(Position(i, j))
            }
        }
        return positionList.toList()
    }
}
