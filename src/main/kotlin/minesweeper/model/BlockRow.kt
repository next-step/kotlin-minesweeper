package minesweeper.model

data class BlockRow(
    val blocks: List<Block>,
) {
    init {
        require(blocks.isNotEmpty()) { "BlockRow는 최소 1개의 Block을 가져야 합니다." }
    }
}
