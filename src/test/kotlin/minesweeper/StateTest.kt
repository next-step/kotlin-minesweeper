package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class StateTest {
    @Test
    fun `게임은 패배할 수 있다`() {
        val state = GameOver(false)

        assertThat(state.isWin()).isFalse()
    }

    @Test
    fun `게임은 승리할 수 있다`() {
        val state = GameOver(true)

        assertThat(state.isWin()).isTrue()
    }

    @Test
    fun `게임이 끝나지 않으면 승리 여부를 확인할 수 없다`() {
        val board = boardFixture(setOf(Position(1, 1)))
        val state = Playing(board)

        assertThatIllegalStateException().isThrownBy { state.isWin() }
            .withMessage("게임이 끝나지 않았습니다.")
    }

    @Test
    fun `게임 중 특정 위치를 열면 게임이 패배 상태로 전환될 수 있다`() {
        val minePosition = setOf(Position(0, 0))
        val board = boardFixture(minePosition)
        val state = Playing(board)

        val nextState = state.toggle(Position(0, 0))

        assertAll(
            { assertThat(nextState).isInstanceOf(GameOver::class.java) },
            { assertThat(nextState.isWin()).isFalse() },
        )
    }

    @Test
    fun `게임 중 특정 위치를 열면 게임이 승리 상태로 전환될 수 있다`() {
        val minePosition = setOf(Position(0, 0), Position(0, 1), Position(1, 0))
        val board = boardFixture(minePosition)
        val state = Playing(board)

        val nextState = state.toggle(Position(1, 1))

        assertAll(
            { assertThat(nextState).isInstanceOf(GameOver::class.java) },
            { assertThat(nextState.isWin()).isTrue() },
        )
    }

    @Test
    fun `게임 중 보드를 표시할 수 있다`() {
        val minePosition = setOf(Position(0, 0))
        val board = boardFixture(minePosition)
        val state = Playing(board)

        val boardDrawing = state.displayBoard()

        assertThat(boardDrawing.hasNext()).isTrue()
    }

    private fun boardFixture(minePositions: Set<Position>): Board {
        val dimensions = Dimensions(2, 2)
        val board = Board(dimensions, minePositions)
        return board
    }
}