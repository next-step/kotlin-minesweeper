package minesweeper.domain

class Blocks(
    val blocks: List<Block>
) {

    companion object {
        fun of(blockCount: Int, mineCount: Int): Blocks {
            return Blocks(
                List(blockCount - mineCount) { CleanBlock() } +
                    List(mineCount) { MineBlock() }
                        .shuffled()
            )
        }
    }
}
