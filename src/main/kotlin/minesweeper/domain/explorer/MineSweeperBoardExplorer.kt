package minesweeper.domain.explorer

import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.position.Position

class MineSweeperBoardExplorer {

    fun explore(board: MineSweeperBoard, startPosition: Position): ExploreResult {
        if (board.isMinePosition(position = startPosition)) {
            return ExploreResult.FailExplore
        }
        board.open(startPosition = startPosition)
        return ExploreResult.SuccessExplore
    }
}
