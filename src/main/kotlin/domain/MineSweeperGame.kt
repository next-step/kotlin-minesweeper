package domain

object MineSweeperGame {
    fun initBoard(weight: Int, height: Int, mineCount: Int): Map<Position, Block> {
        val board = mutableMapOf<Position, Block>()
        val safetyBlockCount = weight * height - mineCount
        val blocks = getBlocks(mineCount, safetyBlockCount)
        var count = 0

        (0 until weight).forEach { x ->
            count = initRow(height, board, x, blocks, count)
        }

        return board.toMap()
    }

    fun validateMineCount(totalBoardCount: Int): (Int) -> Boolean {
        return { count ->
            count <= totalBoardCount
        }
    }

    private fun initRow(
        height: Int,
        board: MutableMap<Position, Block>,
        x: Int,
        blocks: List<Block>,
        count: Int
    ): Int {
        var rowCount = count
        (0 until height).forEach { y ->
            board[Position.getInstance(x, y)] = blocks[rowCount++]
        }

        return rowCount
    }

    private fun getBlocks(mineCount: Int, safetyBlockCount: Int): List<Block> {
        val blocks: MutableList<Block> = (0 until mineCount).map { Mine() }.toMutableList()
        val safetyBlocks = (0 until safetyBlockCount).map { SafetyBlock() }
        return (blocks + safetyBlocks).shuffled()
    }
}
