package tdd.minesweeper.cli

import tdd.minesweeper.converter.mineSweeperPointConverter
import tdd.minesweeper.domain.Point
import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.ui.GameInput
import tdd.minesweeper.ui.GameOutput
import tdd.minesweeper.ui.MineSweeperController
import tdd.minesweeper.ui.request.MineBoardCreateRequest
import tdd.minesweeper.ui.response.MineBoardResponse

class GameCli(
    private val gameInput: GameInput,
    private val gameOutput: GameOutput,
    private val mineSweeperController: MineSweeperController
) {

    fun play() {
        val response: MineBoardResponse = MineBoardCreateRequest(
            width = gameInput.requestWidth(),
            height = gameInput.requestHeight(),
            mineCapacity = gameInput.requestMineCapacity()
        ).let { mineSweeperController.createMineBoard(it) }

        gameOutput.printGameStart()

        recursiveProgressMark(response.mineBoardId, response.gameProgressStatus)
    }

    private fun recursiveProgressMark(mineBoardId: Int, status: GameProgressStatus) {
        runCatching {
            progressMark(mineBoardId, status)
        }.recoverCatching {
            gameOutput.printException(it.message)
            recursiveProgressMark(mineBoardId, status)
        }
    }

    private fun progressMark(id: Int, status: GameProgressStatus) {
        if (status.isContinuable) {
            val result = gameInput.requestMarkingPoint().let { (x, y) ->
                mineSweeperController.markBoard(id, mineSweeperPointConverter.convert(Point(x, y)))
            }
            printMineBoardOrPass(result)
            progressMark(id, result.gameProgressStatus)
        } else {
            gameOutput.printFinished(status)
        }
    }

    private fun printMineBoardOrPass(result: MineBoardResponse) {
        if (result.gameProgressStatus == GameProgressStatus.CONTINUE) {
            gameOutput.printMineBoard(result.symbols)
        }
    }
}
