package view

import domain.MineSweeperGame

object MineSweeperResultView {
    fun drawResult(game: MineSweeperGame) {
        if (game.isWin()) {
            MineSweeperMapView.drawWinResult(game.mineSweeperMap)
            return
        }
        println("Lose Game")
    }
}
