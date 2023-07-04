package minesweeper.domain.game

import minesweeper.domain.data.PositiveInt

class Board(row: PositiveInt, col: PositiveInt, mineCount: PositiveInt) {

    init {
        require((row * col) > mineCount) {
            "지뢰 갯수가 많습니다."
        }
    }

    val cells = List((row * col).number) {
        if (it < mineCount.number) {
            Cell(CellType.MINE)
        } else {
            Cell(CellType.NONE)
        }
    }.shuffled().chunked(row.number)
}
