package minesweeper

class MineBoard(
    val mineCells: Array<MineBoardRow>,
) {
    constructor(twoDimensionMineCell: Array<Array<MineCell>>)
            : this(twoDimensionMineCell.map { MineBoardRow(it) }.toTypedArray())
}

