package dto

import domain.Coordinate
import domain.GameState
import domain.MineBoard
import domain.block.Block

class MineGameDto(gameState: GameState, mineBoard: MineBoard) {
    val state: String = gameState.toString()
    val width: Int = mineBoard.width
    val board: List<BlockDto> = toBlockDto(mineBoard.value)

    private fun toBlockDto(board: Map<Coordinate, Block>): List<BlockDto> {
        return board.toSortedMap()
            .values
            .map(::BlockDto)
    }
}

class BlockDto(block: Block) {
    val isOpened: Boolean = block.isOpened()
    val isMine: Boolean = block.isMine()
    val mineCount: Int = if (!block.isOpened() || block.isMine()) -1 else block.getSurroundingMineCount()
}
