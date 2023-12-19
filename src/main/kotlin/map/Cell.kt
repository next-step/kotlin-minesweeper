package map

sealed class Cell : CellFunc
class None(private val position: Position, var mineCnt: Int = 0) : Cell() {

    override fun searchAround(maxWidth: Int, maxHeight: Int): List<Position> {
        val aroundPosition = mutableListOf<Position>()
        val maxPosition = Position(maxWidth, maxHeight)

        for (addIndex in RelativeDirection.values()) {
            val newPosition = Position(position.x + addIndex.x, position.y + addIndex.y)
            if (checkIndex(newPosition, maxPosition)) aroundPosition.add(newPosition)
        }
        return aroundPosition
    }

    private fun checkIndex(newPosition: Position, maxPosition: Position): Boolean {
        return newPosition.x >= INDEX_ZERO && newPosition.x < maxPosition.x && newPosition.y >= INDEX_ZERO && newPosition.y < maxPosition.y
    }

    companion object {
        private const val INDEX_ZERO = 0
    }
}

object Mine : Cell() {
    override fun searchAround(maxWidth: Int, maxHeight: Int): List<Position> {
        return emptyList()
    }
}

interface CellFunc {
    fun searchAround(maxWidth: Int, maxHeight: Int): List<Position>
}
