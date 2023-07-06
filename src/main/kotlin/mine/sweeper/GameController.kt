package mine.sweeper

import mine.sweeper.application.MineSweeperGame
import mine.sweeper.application.MineSweeperMap
import mine.sweeper.view.InputView
import mine.sweeper.view.OutputView

class GameController(mineSweeperMap: MineSweeperMap) {
    private val game = MineSweeperGame(mineSweeperMap)

    fun playGame() {
        OutputView.noticeGameStart()
        while (game.onProgress()) {
            selectPhase()
        }
        OutputView.noticeGameResult(game.status)
    }

    private fun selectPhase() {
        val position = InputView.getPosition()
        game.open(position)
        OutputView.printMap(game.getResult())
    }
}
