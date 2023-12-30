package minesweeper

import minesweeper.board.BoardElement
import minesweeper.board.GameBoard
import minesweeper.position.Position
import minesweeper.view.Input
import minesweeper.view.Output
import java.lang.NumberFormatException
import java.lang.RuntimeException

class MinesweeperGame (
    private val defaultGameBoard: GameBoard,
    private val minesweeperGameBoard: GameBoard,
    private val boardElement: BoardElement
) {
    private val positionQueue = ArrayDeque<Position>()

    private fun openCell(position: Position): PlayStatus {
        if (minesweeperGameBoard.isMine(position)) {
            return PlayStatus.LOSE
        }
        else if (defaultGameBoard.isVisited(position)) {
            return PlayStatus.OPEN
        }

        positionQueue.add(position)
        defaultGameBoard.visit(position)

        while (positionQueue.isNotEmpty()) {
            val now = positionQueue.removeFirst()
            defaultGameBoard.changeCellValue(now, minesweeperGameBoard.getCell(now))
            val nearPositions = now.nearPositions(boardElement)
            if (!minesweeperGameBoard.isExistMinePosition(nearPositions)) {
                openNearCell(nearPositions)
            }
        }

        return if(defaultGameBoard.areAllCellsOpened()) PlayStatus.WIN else PlayStatus.OPEN
    }

    private fun render() = defaultGameBoard.render()

    private fun openNearCell(positions: List<Position>) {
        positions.forEach {
            if (!defaultGameBoard.isVisited(it)) {
                defaultGameBoard.visit(it)
                defaultGameBoard.changeCellValue(it, minesweeperGameBoard.getCell(it))
                positionQueue.add(it)
            }
        }
    }

    tailrec fun gameStart() {
        val position = convertStringToPosition(boardElement)
        when (this.openCell(position)) {
            PlayStatus.OPEN -> {
                Output.printAny(this.render())
                gameStart()
            }
            PlayStatus.WIN -> Output.printWinGame()
            PlayStatus.LOSE -> Output.printLoseGame()
        }
    }

    private tailrec fun convertStringToPosition(
        boardElement: BoardElement
    ): Position {
        Output.printCellMessage()
        return toPosition(Input.getLine().split(INPUT_POSITION_DELIMITER), boardElement)
            ?: convertStringToPosition(boardElement)
    }

    private fun toPosition(split: List<String>, boardElement: BoardElement): Position? {
        try {
            require(split.size == INPUT_SIZE)
            val position = Position(split[COL], split[ROW])
            if (boardElement.isOutOfRange(position)) {
                return null
            }
            return position
        } catch (e: RuntimeException) {
            return when(e) {
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
