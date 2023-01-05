package domain.state

import domain.Board
import domain.Position

class Running(override val board: Board) : State {
    override fun open(position: Position): State {
        val block = board.getBlockByPosition(position)
        checkNotNull(block) { "잘못된 범위입니다." }
        check(!block.visible) { "이미 열려있는 블록입니다." }

        if (block.isMine()) {
            return Finished(board)
        }
        return Running(board.open(position))
    }
}
