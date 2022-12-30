package minesweeperpractice.domain

data class Blocks(
    val blocks: List<Block>
) {

    companion object {
        fun of(blockCount: Int, mineCount: Int): Blocks {
            require(blockCount > mineCount) { "지뢰 개수가 전체 블록 개수보다 많을 수 없습니다." }

            return Blocks(
                (
                    List(blockCount - mineCount) { CleanBlock() } +
                        List(mineCount) { MineBlock() }
                    ).shuffled()
            )
        }
    }
}
