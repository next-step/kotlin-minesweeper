package minesweeper.state

import minesweeper.model.Board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StateTest {

    private val board: Board get() = Board.EMPTY

    @Test
    fun `Finished 상태는 종료되었다`() {
        // given
        val state = Finished.Win(board)

        // when & then
        assertThat(state.isFinished()).isTrue
    }

    @Test
    fun `Running 상태는 종료되지 않았다`() {
        // given
        val state = Running(board)

        // when & then
        assertThat(state.isFinished()).isFalse
    }
}
