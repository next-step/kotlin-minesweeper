package minesweeper

class MineBoard(
    val mineCells: Array<MineBoardRow>,
) {
    constructor(twoDimensionMineCell: Array<Array<Cell>>)
            : this(twoDimensionMineCell.map { MineBoardRow(it) }.toTypedArray())

    companion object {
        fun createBoard(height: Int, width: Int, mineCount: Int): MineBoard {
            val mineCells: Array<Array<Cell>> = Array(height) { Array(width) { CleanCell() } }
            getRandomPositions(height, width)
                .take(mineCount)
                .forEach { mineCells[it.height][it.width] = MineCell() }
            return MineBoard(mineCells)
        }

        private fun getRandomPositions(height: Int, width: Int) = (0 until height * width).shuffled()
            .map { Position(it / width, it % width) }

    }
}

