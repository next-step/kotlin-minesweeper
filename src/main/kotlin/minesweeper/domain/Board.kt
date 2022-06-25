package minesweeper.domain

class Board(
    val height: Int,
    val width: Int,
    val minesCount: Int
) {

    val cells: List<Cell> = (1..height * width).map {
        if (it <= minesCount) Mine()
        else Opened()
    }.shuffled()
}
