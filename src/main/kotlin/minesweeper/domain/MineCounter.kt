package minesweeper.domain

class MineCounter(private val mapHeight: Int, private val mapWidth: Int, private val mineLocations: MineLocations) {

    fun getMapElement(colNumber: Int, rowNumber: Int): MapElement {
        if (isMine(colNumber, rowNumber)) {
            return MapElement.MINE
        }
        return MapElement.of(getMineCount(colNumber, rowNumber))
    }

    private fun getMineCount(x: Int, y: Int): Int {
        return move.count { (xMove, yMove) -> isMine(x + xMove, y + yMove) }
    }

    private fun isMine(x: Int, y: Int): Boolean {
        if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight) {
            return false
        }
        return mineLocations[y].contains(x)
    }

    companion object {
        private val move = listOf(
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
