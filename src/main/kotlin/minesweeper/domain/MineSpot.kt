package minesweeper.domain

class MineSpot(private val height: FieldHeight, private val width: FieldWidth) : Spot(height, width) {
    override fun isMine(): Boolean {
        return true
    }
}
