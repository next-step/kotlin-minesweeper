package minesweeper.domain

class MineLocations(
    private val mineLocations: List<MineLocationRow>,
    private val mapWidth: Int,
    private val mapHeight: Int,
) {
    operator fun get(index: Int) = mineLocations[index]

    fun getMapElement(colNumber: Int, rowNumber: Int): MapElement {
        if (isMine(colNumber, rowNumber)) {
            return MineMapElement()
        }
        return NumberMapElement(getMineCount(colNumber, rowNumber))
    }

    private fun getMineCount(x: Int, y: Int): Int {
        return MOVE.count { (xMove, yMove) -> isMine(x + xMove, y + yMove) }
    }

    private fun isMine(x: Int, y: Int): Boolean {
        if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight) {
            return false
        }
        return mineLocations[y].contains(x)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MineLocations) {
            return false
        }

        return mineLocations == other.mineLocations && mapWidth == other.mapWidth && mapHeight == other.mapHeight
    }

    override fun hashCode(): Int {
        var result = mineLocations.hashCode()
        result = result * 31 + mapWidth
        result = result * 31 + mapHeight
        return result
    }

    companion object {
        private val MOVE = listOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(1, 1),
        )
    }
}

data class MineLocationRow(private val row: List<Int>) : Iterable<Int> by row
