package domain

object MineSweeperGame {
    fun initBoard(weight: Int, height: Int, mineCount: Int): MineBoard {
        val safetyBlockCount = weight * height - mineCount
        val blocks = getBlocks(mineCount, safetyBlockCount)

        val board = blocks.mapIndexed { index, block ->
            val x = index / weight
            val y = index % weight
            Position(x, y) to block
        }.toMap()

        return MineBoard.getInstance(board)
    }

    fun validateMineCount(totalBoardCount: Int): (Int) -> Boolean {
        return { count ->
            count <= totalBoardCount
        }
    }

    private fun getBlocks(mineCount: Int, safetyBlockCount: Int): List<Block> {
        val blocks: List<Block> = List(mineCount) { Mine() }
        val safetyBlocks = (0 until safetyBlockCount).map { SafetyBlock() }
        return (blocks + safetyBlocks).shuffled()
    }
}
