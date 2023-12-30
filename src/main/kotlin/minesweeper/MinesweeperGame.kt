package minesweeper

import minesweeper.board.BoardElement
import minesweeper.board.PlayingGameBoard
import minesweeper.position.Position
import minesweeper.view.ViewStrategy
import java.lang.NumberFormatException
import java.lang.RuntimeException

class MinesweeperGame (
    private val playingGameBoard: PlayingGameBoard,
    private val boardElement: BoardElement,
    private val viewStrategy: ViewStrategy
) {

    tailrec fun gameStart() {
        val position = convertStringToPosition(boardElement)
        when (this.playingGameBoard.openCell(position, boardElement)) {
            PlayStatus.OPEN -> {
                viewStrategy.printAny(this.playingGameBoard.render())
                gameStart()
            }
            PlayStatus.WIN -> viewStrategy.printWin()
            PlayStatus.LOSE -> viewStrategy.printLose()
        }
    }

    private tailrec fun convertStringToPosition(
        boardElement: BoardElement
    ): Position {
        viewStrategy.printCellMessage()
        return toPosition(viewStrategy.getLine().split(INPUT_POSITION_DELIMITER), boardElement)
            ?: convertStringToPosition(boardElement)
    }

    private fun toPosition(split: List<String>, boardElement: BoardElement): Position? {
        return try {
            require(split.size == INPUT_SIZE)
            val position = Position(split[COL], split[ROW])
            if (boardElement.isOutOfRange(position)) null else position
        } catch (e: RuntimeException) {
            when(e) {
                is IllegalArgumentException, is NumberFormatException -> null
                else -> throw e
            }
        }
    }

    companion object {
        private const val ROW = 0
        private const val COL = 1
        private const val INPUT_SIZE = 2
        private const val INPUT_POSITION_DELIMITER = ", "
    }
}
