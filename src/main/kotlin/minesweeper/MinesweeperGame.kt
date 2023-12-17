package minesweeper

import minesweeper.board.BoardElement
import minesweeper.board.GameBoard
import minesweeper.position.Position

class MinesweeperGame (
    private val defaultGameBoard: GameBoard,
    private val minesweeperGameBoard: GameBoard,
    private val boardElement: BoardElement
) {
    private val positionQueue = ArrayDeque<Position>()

    fun play(position: Position): PlayStatus {
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

    fun render() = defaultGameBoard.render()

    private fun openNearCell(positions: List<Position>) {
        positions.forEach {
            if (!defaultGameBoard.isVisited(it)) {
                defaultGameBoard.visit(it)
                defaultGameBoard.changeCellValue(it, minesweeperGameBoard.getCell(it))
                positionQueue.add(it)
            }
        }
    }
}
