package minesweeper.domain

class SafeSpot(private val height: FieldHeight, private val width: FieldWidth) : Spot(height, width) {
    override fun isMine(): Boolean {
        return false
    }
}
