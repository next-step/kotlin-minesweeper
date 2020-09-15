package minesweeper.domain.square

data class Position(val x: Int, val y: Int) {

    fun isBoundary(height: Int, width: Int): Boolean =
        x == 0 || x == (height + 1) ||
            y == 0 || y == (width + 1)

    operator fun plus(dir: Direction): Position =
        Position(this.x + dir.x, this.y + dir.y)

    override fun toString(): String = "($x, $y)"
}
