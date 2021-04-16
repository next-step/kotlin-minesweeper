package model.board

import model.board.Contents.MINE
import model.board.State.COVERED
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

internal class RowTest {
    @Test
    fun `생성자 인자로 받은 cell list 가 변경되어도 Row 에 영향 주지 않는다`() {
        val cells = mutableListOf(
            Cell.get(MINE, COVERED)
        )

        val row = Row(cells)
        Assertions.assertThat(row.width).isEqualTo(cells.size)

        cells.add(Cell.get(MINE, COVERED))
        Assertions.assertThat(row.width).isNotEqualTo(cells.size)
    }

    @ParameterizedTest
    @EmptySource
    fun `빈 cells 로는 Row 생성 불가`(cells: List<Cell>) {
        assertThrows<IllegalArgumentException> {
            Row(cells)
        }
    }
}
