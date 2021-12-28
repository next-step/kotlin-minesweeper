package controller

import domain.Board
import domain.Height
import domain.MinesPosition
import domain.Position
import domain.Width
import view.OutputView

class BoardController {
    fun create(
        width: Width,
        height: Height,
        mineCount: Int,
    ): Board {
        val minesPosition = MinesPosition.generate(
            maxWidth = width,
            maxHeight = height,
            generateCount = mineCount
        )

        return Board.build(
            width,
            height,
            minesPosition,
        )
    }

    fun isMine(
        board: Board,
        position: Position,
    ): Boolean {
        return board
            .isMine(position)
            .takeIf { it }
            ?.also { OutputView.printLose() }
            ?: false
    }

    fun open(
        board: Board,
        position: Position,
    ) {
        board.open(position)

        OutputView.printBoard(board)
    }
}
