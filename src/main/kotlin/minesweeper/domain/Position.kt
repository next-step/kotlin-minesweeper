package minesweeper.domain

data class Position(val x: Int, val y: Int) {

    lateinit var nearCellPositions: Positions
    fun setNearPositions(boardPositions: Positions) {
        val positions = SearchDirection.values().mapNotNull { it.toPosition() }
        nearCellPositions = Positions(positions.filter { it in boardPositions })
    }

    private fun SearchDirection.toPosition(): Position? {
        val position = Position(x + this.addX, y + addY)
        return if (position.x < 0 || position.y < 0) null else position
    }
}
