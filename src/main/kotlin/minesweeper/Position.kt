package minesweeper

enum class Position(
    val isExist: (row: Int, column: Int, width: Int, height: Int) -> Boolean,
    val getTargetIndex: (index: Int, width: Int) -> Int
) {
    LEFT_UP(
        { row, column, _, _ -> row > 0 && column > 0 },
        { index, width -> index - width - 1 }
    ),
    UP(
        { row, _, _, _ -> row > 0 },
        { index, width -> index - width }
    ),
    RIGHT_UP(
        { row, column, width, _ -> row > 0 && column < width - 1 },
        { index, width -> index - width + 1 }
    ),
    LEFT(
        { _, column, _, _ -> column > 0 },
        { index, _ -> index - 1 }
    ),
    RIGHT(
        { _, column, width, _ -> column < width - 1 },
        { index, _ -> index + 1 }
    ),
    LEFT_DOWN(
        { row, column, _, height -> row < height - 1 && column > 0 },
        { index, width -> index + width - 1 }
    ),
    DOWN(
        { row, _, _, height -> row < height - 1 },
        { index, width -> index + width }
    ),
    RIGHT_DOWN(
        { row, column, width, height -> row < height - 1 && column < width - 1 },
        { index, width -> index + width + 1 }
    )
}
