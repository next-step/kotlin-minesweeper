package minesweeper2.domain

import minesweeper2.model.PositionLocation

enum class FindPosition(val row: Int, val col: Int) {
    LEFT_UP(-1, -1),
    LEFT(-1, 0),
    LEFT_DOWN(-1, 1),
    UP(0, 1),
    DOWN(0, -1),
    RIGHT_UP(1, -1),
    RIGHT(1, 0),
    RIGHT_DOWN(1, 1);

    companion object {
        fun positionLocations(positionLocation: PositionLocation, rows: Int, cols: Int): List<PositionLocation> {
            return values().map { PositionLocation(it.row.plus(positionLocation.row), it.col.plus(positionLocation.col)) }
                .filter { (first, second) ->
                    first >= 0 && second >= 0 && first < rows && second < cols
                }
        }
    }
}
