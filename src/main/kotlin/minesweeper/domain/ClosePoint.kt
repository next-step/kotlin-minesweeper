package minesweeper.domain

class ClosePoint(coordinate: Coordinate, private val hasMine: Boolean) : Point(coordinate) {
    override fun isMine(): Boolean? = null

    override fun open(): Point {
        if (hasMine) {
            val mine = Mine(coordinate)
            mine.setMineCount(mineCount)
            return mine
        }
        val notMine = NotMine(coordinate)
        notMine.setMineCount(mineCount)
        return notMine
    }
}
