package minesweeper.domain.service

import minesweeper.domain.GameBoardState

enum class GameState {
    WIN,
    LOSE,
    CONTINUE,
    ;

    companion object {
        fun from(gameBoardState: GameBoardState): GameState {
            return when {
                gameBoardState.countOfLandmineCells > 0 -> LOSE
                gameBoardState.countOfTotalLandmines == gameBoardState.countOfClosedCells -> WIN
                gameBoardState.countOfClosedCells > gameBoardState.countOfTotalLandmines -> CONTINUE
                else -> throw IllegalArgumentException("유효하지 않은 상태입니다: gameBoardState=$gameBoardState")
            }
        }
    }
}
