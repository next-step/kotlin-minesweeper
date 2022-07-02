package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CellsTest {
    @Test
    internal fun `position에 맞는 cell을 구할 수 있다`() {
        val position = Position(PositiveNumber(1), PositiveNumber(1))
        val cell = Cell(position, Square.Mine)
        val cells = Cells(
            listOf(
                cell,
                Cell(Position(PositiveNumber(2), PositiveNumber(1)), Square.NonMine)
            )
        )
        assertThat(cells.getCell(position.x.toInt(), position.y.toInt())).isEqualTo(cell)
    }
}
