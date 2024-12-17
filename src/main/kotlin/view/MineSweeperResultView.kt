package view

import domain.MineSweeperGame

object MineSweeperResultView {
    fun drawResult(game: MineSweeperGame) {
        if (game.isWin()) {
            println("Win Game")
            MineSweeperMapView.drawMineSweeperMap(game.mineSweeperMap)
            return
        }
        if (game.isLose()) {
            println("Lose Game")
            MineSweeperMapView.drawMineSweeperMap(game.mineSweeperMap)
            return
        }
    }
}
