package dto

import domain.MineBoard
import domain.block.Block

class MineBoardDto(mineBoard: MineBoard) {
    val width: Int = mineBoard.width
    val board: List<BlockDto> = mineBoard.value.toSortedMap().map { it.value }.map(::BlockDto)
}

class BlockDto(block: Block) {
    val isChecked: Boolean = block.isChecked()
    val isMine: Boolean = block.isMine()
    val mineCount: Int = if (!block.isChecked() || block.isMine()) -1 else block.surroundingMineCount().value
}
