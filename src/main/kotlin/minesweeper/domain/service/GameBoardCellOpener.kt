package minesweeper.domain.service

import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Location
import minesweeper.domain.strategy.OpenCellStrategyHandler

class GameBoardCellOpener(
    private val openCellStrategyHandler: OpenCellStrategyHandler = OpenCellStrategyHandler(),
) {
    fun openGameBoardCell(
        gameBoard: GameBoard,
        location: Location,
    ): GameBoard {
        val targetCell = gameBoard.find(location) ?: throw IllegalArgumentException("해당 위치를 가진 셀이 존재하지 않습니다: location=$location")
        val openCellStrategy = openCellStrategyHandler.findStrategy(targetCell)
        return GameBoard.from(openCellStrategy.open(gameBoard.cells, targetCell))
    }
}
