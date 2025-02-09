package tdd.minesweeper.ui

import tdd.minesweeper.domain.Game
import tdd.minesweeper.domain.GameState

class MineSweeperApp(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play() {
        val game = initializeGame()

        // 정상적으로 게임 초기화 후 시작 메시지 출력
        outputView.printGameStarted()

        playContinue(game)

        handleGameEnd(game)
    }

    private tailrec fun initializeGame(): Game {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val countOfMines = inputView.inputCountOfMines()

        val result =
            runCatching {
                Game.from(
                    height = height,
                    width = width,
                    countOfMines = countOfMines,
                )
            }

        return if (result.isSuccess) {
            result.getOrThrow()
        } else {
            outputView.printError(result.exceptionOrNull()?.message ?: "보드 생성 중 알 수 없는 에러가 발생했습니다")
            initializeGame() // 재귀 호출
        }
    }

    private fun playContinue(game: Game) {
        while (game.state() == GameState.CONTINUE) {
            outputView.displayBoard(game.board)

            val location = inputView.inputLocation()

            runCatching {
                game.open(location)
            }.getOrElse { error ->
                outputView.printError(error.message ?: "셀 오픈 중 알 수 없는 에러가 발생했습니다")
            }
        }
    }

    private fun handleGameEnd(game: Game) {
        outputView.printGameEnded(game.state())
    }
}
