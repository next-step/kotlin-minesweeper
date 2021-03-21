package minesweeper.domain

data class Position(val x: Int, val y: Int) {
    fun valid(width: Int, height: Int): Boolean = !negative() && !outside(width, height)

    private fun outside(width: Int, height: Int) = x > width - 1 || y > height - 1

    private fun negative() = x < 0 || y < 0

    override fun toString(): String {
        return "$x, $y"
    }
}
