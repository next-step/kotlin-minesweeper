package mine.sweeper

import mine.sweeper.application.MineSweeperGame
import mine.sweeper.application.MineSweeperMap
import mine.sweeper.view.InputView
import mine.sweeper.view.OutputView

class GameController(
    mineSweeperMap: MineSweeperMap,
    private val game: MineSweeperGame = MineSweeperGame(mineSweeperMap)
) {
    fun playGame() {
        OutputView.noticeGameStart()
        while (game.isProgress) {
            selectPhase()
        }
        OutputView.noticeGameResult(game.gameResult)
    }

    private fun selectPhase() {
        val position = InputView.getPosition()
        game.select(position)
        OutputView.printMap(game.fields)
    }
}
