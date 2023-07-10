package view.output

import domain.GameResult

class GameEndOutputView(gameResult: GameResult) {
    init {
        if (gameResult.isWin()) {
            renderWinMessage()
        } else {
            renderLoseMessage()
        }
    }

    private fun renderWinMessage() {
        println("Win Game.")
    }

    private fun renderLoseMessage() {
        println("Lose Game.")
    }
}
