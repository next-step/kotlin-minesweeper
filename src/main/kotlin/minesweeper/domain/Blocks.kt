package minesweeper.domain

data class Blocks(
    val blocks: List<Block>
) {

    companion object {
        fun of(wholeCount: Int, mineLocation: List<Int>): Blocks {
            val blocks: MutableList<Block> = MutableList(wholeCount) { CleanBlock() }
            mineLocation.forEach { blocks[it] = MineBlock() }
            return Blocks(blocks)
        }
    }
}
