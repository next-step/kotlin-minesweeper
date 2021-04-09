package minesweeper.domain

enum class Position(
    val isExist: (location: Location, width: Int, height: Int) -> Boolean,
    val getTargetIndex: (index: Int, width: Int) -> Int
) {
    LEFT_UP(
        { location, _, _ -> location.row > 0 && location.column > 0 },
        { index, width -> index - width - 1 }
    ),
    UP(
        { location, _, _ -> location.row > 0 },
        { index, width -> index - width }
    ),
    RIGHT_UP(
        { location, width, _ -> location.row > 0 && location.column < width - 1 },
        { index, width -> index - width + 1 }
    ),
    LEFT(
        { location, _, _ -> location.column > 0 },
        { index, _ -> index - 1 }
    ),
    RIGHT(
        { location, width, _ -> location.column < width - 1 },
        { index, _ -> index + 1 }
    ),
    LEFT_DOWN(
        { location, _, height -> location.row < height - 1 && location.column > 0 },
        { index, width -> index + width - 1 }
    ),
    DOWN(
        { location, _, height -> location.row < height - 1 },
        { index, width -> index + width }
    ),
    RIGHT_DOWN(
        { location, width, height -> location.row < height - 1 && location.column < width - 1 },
        { index, width -> index + width + 1 }
    );

    companion object {
        private fun getNearByLocation(location: Location, width: Int, height: Int): List<Position> {
            return values().filter { it.isExist(location, width, height) }
        }

        fun getIndexesNearByLocation(location: Location, width: Int, height: Int): List<Int> {
            return getNearByLocation(location, width, height)
                .map { it.getTargetIndex(location.getConvertIndex(width), width) }
        }
    }
}
