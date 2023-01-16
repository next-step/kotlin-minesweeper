package minesweeper.controller

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.block.MineBlock
import minesweeper.view.ConsoleResultView
import minesweeper.view.FakeInputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameControllerTest {

    @Test
    fun `게임 실행이 완료되면 마지막 상태의 유저 요구사항에 맞는 Board를 반환한다`() {

        val userInputHeight = 2
        val userInputWidth = 2
        val userInputMineCount = 1

        val controller =
            GameController(FakeInputView(userInputHeight, userInputWidth, userInputMineCount), ConsoleResultView())

        val board = controller.run()
        assertThat(board).isExactlyInstanceOf(MineSweeperBoard::class.java)
        assertThat(board!!.state.size).isEqualTo(userInputWidth * userInputHeight)
        assertThat(board.state.values.count { it is MineBlock }).isEqualTo(userInputMineCount)
    }

    @Test
    fun `게임 실행 도중 예외가 발생하면 null을 반환한다`() {

        val userInputHeight = 2
        val userInputWidth = 2
        val userInputMineCount = 5

        val controller =
            GameController(FakeInputView(userInputHeight, userInputWidth, userInputMineCount), ConsoleResultView())

        val board = controller.run()
        assertThat(board).isEqualTo(null)
    }
}
