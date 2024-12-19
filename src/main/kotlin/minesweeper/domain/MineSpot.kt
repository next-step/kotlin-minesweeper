package minesweeper.domain

class MineSpot(private val y: Int, private val x: Int) : Spot(y, x) {
    override fun isMine(): Boolean {
        return true
    }
}
