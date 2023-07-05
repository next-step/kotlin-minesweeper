package mine.sweeper

import mine.sweeper.application.FieldsManager
import mine.sweeper.application.MineSweeperGame
import mine.sweeper.application.value.GameStatus
import mine.sweeper.domain.Vulture
import mine.sweeper.view.InputView
import mine.sweeper.view.OutputView
import mine.sweeper.view.dto.MapSize

class GameController {

    lateinit var game: MineSweeperGame
    fun create() {
        val mapSize = MapSize(InputView.getHeight(), InputView.getWidth())
        val fieldsManager = FieldsManager(mapSize)
        val vulture = Vulture(mapSize)
        game = MineSweeperGame(fieldsManager, vulture)
    }

    fun setMines() {
        game.setMine(InputView.getMines())
        OutputView.noticeGameStart()
    }

    fun start() {
        var status: GameStatus
        do {
            val position = InputView.getOpenPosition()
            status = game.open(position)
            status = noticeRetry(status)
            OutputView.printMap(game.getResult())
        } while (status == GameStatus.ON_PROGRESS)

        OutputView.noticeGameResult(status)
    }

    private fun noticeRetry(status: GameStatus): GameStatus {
        var status1 = status
        if (status1 == GameStatus.RE_TRY) {
            OutputView.noticeWrongPosition()
            status1 = GameStatus.ON_PROGRESS
        }
        return status1
    }
}
