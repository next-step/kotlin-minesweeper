package minesweeper.domain

class MineBoard(
    val mineCells: Array<MineBoardRow>,
) {
    private val height: Int
        get() = mineCells.size
    private val width: Int
        get() = mineCells[0].width

    constructor(height: Int, width: Int) : this(Array(height) { MineBoardRow(width) })

    private fun plantMine(height: Int, width: Int) {
        this.mineCells[height].plantMine(width)
    }

    private fun incrementNearMineCount(position: Position) {
        this.mineCells[position.height]
            .incrementNearMineCount(position.width)
    }

    private fun updateNearMineCount(position: Position) {
        position.getNearPositions()
            .filter { it.rangeIn(height, width) }
            .forEach(::incrementNearMineCount)
    }

    companion object {
        fun createBoard(height: Int, width: Int, mineCount: Int): MineBoard {
            val mineBoard = MineBoard(height, width)

            getRandomPositions(height, width)
                .take(mineCount)
                .forEach {
                    mineBoard.plantMine(it.height, it.width)
                    mineBoard.updateNearMineCount(it)
                }
            return mineBoard
        }

        private fun getRandomPositions(height: Int, width: Int) = (0 until height * width).shuffled()
            .map { Position(it / width, it % width) }
    }
}
