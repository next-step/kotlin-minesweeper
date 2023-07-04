package minesweeper.domain

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Coordinate

enum class CoordinateFinder(
    val find: (Coordinate) -> Coordinate,
) {
    SOUTH({ it.down() }),

    SOUTH_WEST({ it.down().left() }),

    WEST({ it.left() }),

    NORTH_WEST({ it.up().left() }),

    NORTH({ it.up() }),

    NORTH_SOUTH({ it.up().right() }),

    EAST({ it.right() }),

    SOUTH_EAST({ it.down().right() }),

    ;

    companion object {
        fun nearCoordinates(cell: Cell): List<Coordinate> = values().map { it.find(cell.coordinate) }
    }
}
