package minesweeper.domain

class MineBoard(
    val mineCells: Array<MineBoardRow>,
) {
    constructor(height: Int, width: Int) : this(Array(height) { MineBoardRow(width) })

    private fun plantMine(height: Int, width: Int) {
        this.mineCells[height].plantMine(width)
    }

    companion object {

        fun createBoard(height: Int, width: Int, mineCount: Int): MineBoard {
            val mineBoard = MineBoard(height, width)

            getRandomPositions(height, width)
                .take(mineCount)
                .forEach { mineBoard.plantMine(it.height, it.width) }

            return mineBoard
        }

        private fun getRandomPositions(height: Int, width: Int) = (0 until height * width).shuffled()
            .map { Position(it / width, it % width) }
    }
}

