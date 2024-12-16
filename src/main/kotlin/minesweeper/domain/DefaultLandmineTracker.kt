package minesweeper.domain

class DefaultLandmineTracker : LandmineTracker {
    override fun withUpdatedAdjacentMineCounts(
        gameBoard: GameBoard,
        landmineLocation: Location,
    ): GameBoard {
        // 1.지뢰 주변 8칸의 Location 목록 구하기
        val adjacentLocations = AdjacentLocationDirection.allAdjacentLocations(landmineLocation)

        return GameBoard.from(
            gameBoard.rows.map { row ->
                row.cells().map { cell ->
                    if (cell.location() in adjacentLocations && cell is BasicCell) {
                        cell.withIncrementedNumberOfAdjacentMines()
                    } else {
                        cell
                    }
                }
            },
        )
    }
}

enum class AdjacentLocationDirection(val offset: Pair<Int, Int>) {
    TOP_LEFT(Pair(-1, -1)),
    TOP(Pair(-1, 0)),
    TOP_RIGHT(Pair(-1, 1)),
    LEFT(Pair(0, -1)),
    RIGHT(Pair(0, 1)),
    BOTTOM_LEFT(Pair(1, -1)),
    BOTTOM(Pair(1, 0)),
    BOTTOM_RIGHT(Pair(1, 1)),
    ;

    companion object {
        fun allAdjacentLocations(location: Location) =
            entries.map {
                Location(
                    location.row + it.offset.first,
                    location.column + it.offset.second,
                )
            }
    }
}
