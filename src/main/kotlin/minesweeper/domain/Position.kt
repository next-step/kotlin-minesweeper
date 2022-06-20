package minesweeper.domain

data class Position(val x: Int, val y: Int) {

    lateinit var nearCellPositions: Positions
    fun setNearPositions(boardPositions: Positions) {
        val aroundPositions = around()
        nearCellPositions = boardPositions.include(aroundPositions)
    }

    fun getNearMineCount(minePositions: Positions): Int {
        return nearCellPositions.count { it in minePositions }
    }

    private fun around(): List<Position> {
        return SearchDirection.values().mapNotNull { this.move(it) }
    }

    private fun move(searchDirection: SearchDirection): Position? {
        val position = Position(this.x + searchDirection.addX, this.y + searchDirection.addY)
        return if (position.x < 0 || position.y < 0) null else position
    }
}
