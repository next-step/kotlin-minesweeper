package minesweeper

class MineBoard(
    val mineCells: Array<MineBoardRow>,
) {
    constructor(twoDimensionMineCell: Array<Array<Cell>>)
            : this(twoDimensionMineCell.map { MineBoardRow(it) }.toTypedArray())
}

