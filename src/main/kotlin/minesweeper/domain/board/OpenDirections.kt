package minesweeper.domain.board

enum class OpenDirections(private val x: Int, private val y: Int) {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    fun nextCoordinate(x: Int, y: Int): Pair<Int, Int> = (Math.addExact(this.x, x) to Math.addExact(this.y, y))
}
