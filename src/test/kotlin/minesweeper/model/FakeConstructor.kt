package minesweeper.model

fun Board(
    width: Int,
    height: Int,
    mineCount: Int = 0
) = Board.create(Width(width), Height(height), MineCount(mineCount))
