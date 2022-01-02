package minesweeper.domain.directions

import minesweeper.domain.block.Position
import java.lang.Math.addExact

enum class OpenDirections(private val x: Int, private val y: Int) : Directions {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    override fun nextPosition(position: Position): Position =
        Position(addExact(this.x, position.x), addExact(this.y, position.y))
}
