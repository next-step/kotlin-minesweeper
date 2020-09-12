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
        val rowSize = minePlate.value.size
        val colSize = minePlate.value[0].column.blocks.size

        for (y in 0 until rowSize) {
            for (x in 0 until colSize) {
                val mineCount = calculateMineCount(x, y, minePlate)
                minePlate.value[y].column.blocks[x].mineCount = mineCount
            }
        }

        return minePlate
    }

    fun calculateMineCount(x: Int, y: Int, minePlate: MinePlate): Int {
        var mineCount = 0
        val startXPosition = x - 1
        val endXPosition = x + 1
        val startYPosition = y - 1
        val endYPosition = y + 1

        for (dy in startYPosition..endYPosition) {
            for (dx in startXPosition..endXPosition) {
                try {
                    if(minePlate.value[dy].column.blocks[dx] == minePlate.value[x].column.blocks[y]) {
                        continue
                    }
                    if (minePlate.value[dy].column.blocks[dx].isMine()) {
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
        for (j in minePlate.value.indices) {
            for (i in minePlate.value[j].column.blocks.indices) {
                positionList.add(Position(i, j))
            }
        }
        return positionList.toList()
    }
}
