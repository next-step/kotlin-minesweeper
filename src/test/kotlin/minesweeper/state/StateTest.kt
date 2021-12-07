package minesweeper.state

import minesweeper.model.Board
import minesweeper.model.Cell
import minesweeper.model.Cells
import minesweeper.model.MineCount
import minesweeper.model.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StateTest {

    private val board: Board get() = Board.EMPTY

    @Test
    fun `Finished 상태는 종료되었다`() {
        // given
        val state: State = Finished.Win(board)

        // when & then
        assertThat(state.isFinished()).isTrue
    }

    @Test
    fun `Running 상태는 종료되지 않았다`() {
        // given
        val state: State = Running(board)

        // when & then
        assertThat(state.isFinished()).isFalse
    }

    @Test
    fun `Running상태에서 Position을 열고 모든 Cell이 열려 있다면 Win이 된다`() {
        // given
        val board = Board(2, 1, 0)
        val state: State = Running(board)

        // when
        val actual = state.tryOpen(Position(1, 1))

        // then
        assertThat(actual).isInstanceOf(Finished.Win::class.java)
    }

    @Test
    fun `Running 상태에서 지뢰를 열면 Lose가 된다`() {
        // given
        val board = Board(2, 2, 4)
        val state: State = Running(board)

        // when
        val actual = state.tryOpen(Position(1, 1))

        // then
        assertThat(actual).isInstanceOf(Finished.Lose::class.java)
    }

    @Test
    fun `Cell을 열고 아직 열지 않은 Cell이 있다면 Running이 된다`() {
        // given
        // 1 1
        // 1 *
        val cells = listOf(
            Cell.Number(MineCount.valueOf(1), Position(1, 1)),
            Cell.Number(MineCount.valueOf(1), Position(1, 2)),
            Cell.Number(MineCount.valueOf(1), Position(2, 1)),
            Cell.Mine(Position(2, 2)),
        )
        val board = Board(Cells(cells))
        val state: State = Running(board)

        // when
        val actual = state.tryOpen(Position(1, 1))

        // then
        assertThat(actual).isInstanceOf(Running::class.java)
    }
}
