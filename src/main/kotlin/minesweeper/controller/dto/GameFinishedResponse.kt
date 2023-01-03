package minesweeper.controller.dto

import minesweeper.domain.GameState
import minesweeper.view.model.GameFinished

data class GameFinishedResponse(
    val gameFinished: GameFinished
) {
    companion object {
        fun from(gameResult: GameState): GameFinishedResponse {
            return GameFinishedResponse(
                when (gameResult) {
                    GameState.WIN -> GameFinished.WIN_GAME
                    GameState.LOSE -> GameFinished.LOSE_GAME
                    GameState.PLAYING -> throw IllegalStateException()
                }
            )
        }
    }
}
