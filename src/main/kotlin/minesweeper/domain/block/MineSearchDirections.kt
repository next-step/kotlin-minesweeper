package minesweeper.domain.block

import java.lang.Math.addExact

enum class MineSearchDirections(private val x: Int, private val y: Int) {
    NORTH(0, -1),
    NORTH_EAST(1, -1),
    EAST(1, 0),
    SOUTH_EAST(1, 1),
    SOUTH(0, 1),
    SOUTH_WEST(-1, 1),
    WEST(-1, 0),
    NORTH_WEST(-1, -1);

    fun nextCoordinate(x: Int, y: Int): Pair<Int, Int> = (addExact(this.x, x) to addExact(this.y, y))
}
