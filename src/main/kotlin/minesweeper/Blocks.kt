package minesweeper

class Blocks(
    val blocks: List<Block>
) {

    fun shuffle(): Blocks {
        return Blocks(blocks.shuffled())
    }

    companion object {
        fun of(wholeCount: Int, mineCount: Int): Blocks {
            return Blocks(
                List(wholeCount - mineCount) { CleanBlock() } +
                    List(mineCount) { MineBlock() }
            )
        }
    }
}
