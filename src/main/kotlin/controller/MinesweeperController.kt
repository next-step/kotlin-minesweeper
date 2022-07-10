package controller

import domain.MinePositionsFactory
import domain.Minesweeper
import domain.MinesweeperInfo
import view.input.InputView
import view.input.UserInputRequest
import view.input.converter.StringToIntConverter
import view.output.OutputView
import view.output.converter.MinesweeperConverter

object MinesweeperController {
    private const val GUIDANCE_MESSAGE_START_MINESWEEPER = "지뢰찾기 게임 시작"

    fun startGame(minePositionsFactory: MinePositionsFactory) {
        val minesweeperInfo = MinesweeperInfo(
            rowCount = getUserInput(InputType.ROW),
            columnCount = getUserInput(InputType.COLUMN),
            mineCount = getUserInput(InputType.MINE)
        )

        val minesweeper = Minesweeper.of(minesweeperInfo, minePositionsFactory)
        OutputView.printlnOnlyMessage(GUIDANCE_MESSAGE_START_MINESWEEPER)
        OutputView.print(minesweeper, MinesweeperConverter)
    }

    private fun getUserInput(inputType: InputType): Int {
        val userInputRequest = UserInputRequest(
            message = inputType.message,
            inputConverter = StringToIntConverter
        )

        return InputView.receiveUserInput(userInputRequest)
    }

    private enum class InputType(val message: String) {
        ROW("높이를 입력하세요"),
        COLUMN("너비를 입력하세요"),
        MINE("지뢰는 몇 개인가요?")
    }
}
