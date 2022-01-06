package minesweeper.state

import minesweeper.domain.Board
import minesweeper.domain.Cells
import minesweeper.domain.Column
import minesweeper.domain.Height
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.Row
import minesweeper.domain.Width
import minesweeper.domain.state.Lose
import minesweeper.domain.state.Running
import minesweeper.domain.state.Win
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RunningTest {

    private lateinit var positionList: List<Position>
    private lateinit var minePositionList: List<Position>

    @BeforeEach
    internal fun setUp() {
        positionList = Positions.of(Width.from(3), Height.Companion.from(3)).positions

        minePositionList = listOf(
            Position(Row.from(0), Column.from(0)),
            Position(Row.from(0), Column.from(2)),
        )
        // (*, C, C)
        // (C, C, C)
        // (*, C, C)
    }

    @Test
    fun `Running은 Finished 상태이다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)
        val running = Running(board)

        val isLoseFinished = running.isFinished()

        assertThat(isLoseFinished).isFalse
    }

    @Test
    fun `Running은 셀을 open했을 때 SafetyCell이 전부 open되지 않으면 Running이다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)
        val running = Running(board)

        val openPosition = Position.of(Row.from(2), Column.from(0))
        val openState = running.open(openPosition)

        assertThat(openState).isInstanceOf(Running::class.java)
    }

    @Test
    fun `Running은 셀을 open했을 때 SafetyCell이 전부 open되면 Win이다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)
        val running = Running(board)

        val openPosition = Position.of(Row.from(2), Column.from(0))
        val openPosition2 = Position.of(Row.from(0), Column.from(1))
        val openState = running.open(openPosition).open(openPosition2)

        assertThat(openState).isInstanceOf(Win::class.java)
    }

    @Test
    fun `Running은 open한 셀이 MineCell이면 Lose이다`() {
        val cells = Cells.of(positionList, minePositionList)
        val board = Board.from(cells)
        val running = Running(board)

        val openPosition = Position.of(Row.from(0), Column.from(0))
        val openState = running.open(openPosition)

        assertThat(openState).isInstanceOf(Lose::class.java)
    }
}
