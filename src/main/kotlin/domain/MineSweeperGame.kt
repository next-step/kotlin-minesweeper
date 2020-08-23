package domain

object MineSweeperGame {
    private fun getBlocks(mineCount: Int, safetyBlockCount: Int): List<Block> {
        val blocks: MutableList<Block> = (0 until mineCount).map { Mine() }.toMutableList()
        val safetyBlocks = (0 until safetyBlockCount).map { SafetyBlock() }
        return (blocks + safetyBlocks).shuffled()
    }
}
