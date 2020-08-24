import model.MineCount
import model.MinePlate
import model.Position

class MinesweeperGame() {
    fun start(minePlate: MinePlate, mineCount: MineCount): MinePlate {
        val mineList = positionList(minePlate).shuffled().take(mineCount.value.value)

        mineList.forEach { minePlate.value[it.y][it.x].changeToMine() }

        return minePlate
    }

    private fun positionList(minePlate: MinePlate): List<Position> {
        val positionList: MutableList<Position> = mutableListOf()
        for (i in minePlate.value.indices) {
            for (j in minePlate.value[i].indices) {
                positionList.add(Position(j, i))
            }
        }
        return positionList.toList()
    }
}
