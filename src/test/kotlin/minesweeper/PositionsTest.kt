package minesweeper

import minesweeper.domain.Column
import minesweeper.domain.Height
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.Row
import minesweeper.domain.Width
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionsTest {

    @Test
    fun `width와 height을 입력하면 width * height 크기의 Positions를 생성할 수 있다`() {
        val width = Width.from(5)
        val height = Height.from(5)

        val positions = Positions.of(width, height)

        assertThat(positions.positions.size).isEqualTo(25)
    }

    @Test
    fun `Position List를 통해 Positions를 생성할 수 있다`() {
        val positionList = listOf(
            Position.of(Row.from(0), Column.from(0)),
            Position.of(Row.from(1), Column.from(0)),
            Position.of(Row.from(0), Column.from(1)),
            Position.of(Row.from(2), Column.from(1)),
            Position.of(Row.from(1), Column.from(2)),
            Position.of(Row.from(2), Column.from(2)),
        )

        val positions = Positions.from(positionList)

        assertThat(positions.positions.size).isEqualTo(6)
    }
}
