package map

sealed class Cell(private var isOpen: Boolean = false) : CellFunc {

    override fun openCell() {
        isOpen = true
    }

    override fun isOpen() = isOpen
}

class None(private val position: Position, var mineCnt: Int = 0) : Cell() {

    override fun searchAround(maxHeight: Int, maxWidth: Int): List<Position> {
        val aroundPosition = mutableListOf<Position>()
        val maxPosition = Position(maxHeight, maxWidth)

        for (addIndex in RelativeDirection.values()) {
            val newPosition = Position(position.y + addIndex.x, position.x + addIndex.y)
            if (checkIndex(newPosition, maxPosition)) aroundPosition.add(newPosition)
        }

        return aroundPosition
    }

    private fun checkIndex(newPosition: Position, maxPosition: Position): Boolean {
        return newPosition.y >= INDEX_ZERO &&
            newPosition.y < maxPosition.y &&
            newPosition.x >= INDEX_ZERO &&
            newPosition.x < maxPosition.x
    }

    companion object {
        private const val INDEX_ZERO = 0
    }
}

class Mine : Cell() {

    override fun searchAround(maxHeight: Int, maxWidth: Int): List<Position> {
        return emptyList()
    }
}

interface CellFunc {
    fun searchAround(maxHeight: Int, maxWidth: Int): List<Position>

    fun openCell()

    fun isOpen(): Boolean
}
