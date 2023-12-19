package map

sealed class Cell : CellFunc
class None(private val x: Int, private val y: Int, var mineCnt: Int = 0) : Cell() {

    override fun searchAround(): List<Position> {
        val aroundPosition = mutableListOf<Position>()

        for (addIndex in RelativeDirection.values()) {
            val newX = x + addIndex.x
            val newY = y + addIndex.y
            aroundPosition.add(Position(newX, newY))
        }
        return aroundPosition
    }
}

object Mine : Cell() {
    override fun searchAround(): List<Position> {
        return emptyList()
    }
}

interface CellFunc {
    fun searchAround(): List<Position>
}
