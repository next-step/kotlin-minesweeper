package minesweeper.vo

import minesweeper.domain.Board

class BoardVO(
    val rows: List<RowVO>
) {
    companion object {
        operator fun invoke(board: Board): BoardVO {
            return BoardVO(board.map { RowVO(it) })
        }
    }
}
