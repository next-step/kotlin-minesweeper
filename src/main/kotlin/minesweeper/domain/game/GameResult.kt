package minesweeper.domain.game

import minesweeper.domain.Board

data class GameResult(val state: State, val board: Board) {

    fun endGame(): Boolean {
        return state != State.PLAY
    }
}
