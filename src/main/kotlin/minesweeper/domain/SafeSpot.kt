package minesweeper.domain

class SafeSpot(private val y: Int, private val x: Int) : Spot(y, x) {
    override fun isMine(): Boolean {
        return false
    }
}
