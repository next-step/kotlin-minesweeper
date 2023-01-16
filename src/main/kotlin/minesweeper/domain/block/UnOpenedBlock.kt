package minesweeper.domain.block

sealed class UnOpenedBlock(private var hideBlock: OpenedBlock) : Block() {

    fun open(): OpenedBlock {
        return hideBlock
    }
}
