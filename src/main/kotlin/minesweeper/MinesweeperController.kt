package minesweeper

import minesweeper.application.GenerateMinesweeperCommand
import minesweeper.application.MinesweeperService
import minesweeper.domain.CompletedGame
import minesweeper.domain.PlayableGame
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

class MinesweeperController(
    private val minesweeperService: MinesweeperService,
) {
    fun start() {
        val height = InputView.getHeight()
        val width = InputView.getWidth()
        val mineCount = InputView.getMineCount()

        var game =
            minesweeperService.newGame(
                GenerateMinesweeperCommand(
                    height = height,
                    width = width,
                    mineCount = mineCount,
                ),
            )

        ResultView.start()

        while (game is PlayableGame) {
            val (y, x) = InputView.getCoordinates()
            game = game.open(y, x)
            if (game is PlayableGame) {
                ResultView.render(game)
            }
        }

        ResultView.result(game as CompletedGame)
    }
}
