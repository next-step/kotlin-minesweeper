package minesweeper_refactor.domain.game

import minesweeper_refactor.domain.block.OpenState
import minesweeper_refactor.domain.board.MatchState
import minesweeper_refactor.domain.board.MinesweeperBoard
import minesweeper_refactor.domain.coordinate.FourDirectionsDecision

class MinesweeperGame(private val minesweeperBoard: MinesweeperBoard) {

    tailrec fun start(gameEvent: GameEvent): MatchState {
        if (minesweeperBoard.isWinGame()) return MatchState.WIN

        val openCoordinate = gameEvent.openCoordinateEvent()

        return when (minesweeperBoard.openBlock(coordinate = openCoordinate)) {
            OpenState.MINE -> MatchState.LOSE
            OpenState.DO_NOTHING -> start(gameEvent = gameEvent)
            OpenState.CHAIN_OPEN -> {
                minesweeperBoard.openAroundBlock(
                    coordinates = openCoordinate.toAroundDirections(aroundDecision = FourDirectionsDecision),
                )
                gameEvent.currentBoardEvent(this)
                start(gameEvent = gameEvent)
            }
        }
    }
}
