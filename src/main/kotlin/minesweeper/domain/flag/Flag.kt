package minesweeper.domain.flag

sealed class Flag(private val blockState: BlockState) {

    fun getOpenedBlockStatus(): BlockState = blockState
}
