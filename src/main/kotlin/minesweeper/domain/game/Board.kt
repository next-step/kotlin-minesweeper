package minesweeper.domain.game

import minesweeper.domain.data.PositiveNumber

class Board(width: PositiveNumber, height: PositiveNumber, mine: List<Coordinate>) {

    init {
        require((width * height) > mine.size) {
            "지뢰 갯수가 많습니다."
        }
    }

    val board: List<Row> = (PositiveNumber.BASE_NUMBER until height.number).map { col ->
        val row = (PositiveNumber.BASE_NUMBER until width.number).map { row ->
            val isMine = mine.contains(Coordinate(row, col))
            createCell(isMine)
        }
        Row(row)
    }

    private fun createCell(isMine: Boolean): Cell = if (isMine) Cell(CellType.MINE) else Cell(CellType.NONE)
}
