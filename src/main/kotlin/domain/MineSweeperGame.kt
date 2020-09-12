package domain

object MineSweeperGame {
    fun initBoard(weight: Int, height: Int, mineCount: Int): MineBoard {
        val safetyBlockCount = weight * height - mineCount
        val blocks = getBlocks(mineCount, safetyBlockCount)
        val board = getInitBoard(blocks, weight)
        val minePosition = board.filterValues { it.isMine() }
        getResultBoard(minePosition, board)
        return MineBoard(board)
    }

    private fun getResultBoard(
        minePosition: Map<Position, Block>,
        board: MutableMap<Position, Block>
    ) {
        minePosition.forEach { position ->
            val mineX = position.key.getX()
            val mineY = position.key.getY()

            Direction.values().forEach {
                board[it.apply(mineX, mineY)]?.setMineCount()
            }
        }
    }

    private fun getInitBoard(
        blocks: List<Block>,
        weight: Int
    ): MutableMap<Position, Block> {
        return blocks.mapIndexed { index, block ->
            val x = index / weight
            val y = index % weight
            Position(x, y) to block
        }.toMap().toMutableMap()
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
