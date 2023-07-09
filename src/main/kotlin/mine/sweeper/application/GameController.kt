package mine.sweeper.application

import mine.sweeper.domain.Game
import mine.sweeper.view.InputView
import mine.sweeper.view.OutputView

class GameController(
    private val game: Game
) {
    fun playGame() {
        OutputView.noticeGameStart()
        while (game.isProgress) {
            val position = InputView.getPosition()
            game.select(position)
            OutputView.printMap(game.fields)
        }
        OutputView.noticeGameResult(game.gameResult)
    }
}
