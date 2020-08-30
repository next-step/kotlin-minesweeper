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
                minePlate.value[y].column.blocks[x].setMineCount(x, y, minePlate)
            }
        }

        return minePlate
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
