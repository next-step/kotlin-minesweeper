package minesweeper.board

import minesweeper.PlayStatus
import minesweeper.position.Position

class PlayingGameBoard (
    private val defaultGameBoard: GameBoard,
    private val minesweeperGameBoard: GameBoard
) {
    private val positionQueue = ArrayDeque<Position>()

    fun openCell(position: Position, boardElement: BoardElement): PlayStatus {
        if (minesweeperGameBoard.isMine(position)) {
            return PlayStatus.LOSE
        }
        else if (defaultGameBoard.isVisited(position)) {
            return PlayStatus.OPEN
        }

        findOpenCellAndOpen(position, boardElement)

        return if(defaultGameBoard.areAllCellsOpened()) PlayStatus.WIN else PlayStatus.OPEN
    }

    fun render() = defaultGameBoard.render()

    private fun findOpenCellAndOpen(position: Position, boardElement: BoardElement) {
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
    }

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
