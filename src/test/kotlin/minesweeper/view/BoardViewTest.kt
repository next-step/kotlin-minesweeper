package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.Cells
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringWriter

class BoardViewTest {
    @Test
    internal fun `높이 너비로 화면에 출력한다`() {
        val out = StringWriter()
        BoardView(Cells(listOf(Cell(), Cell(), Cell(), Cell()), width = 2), out).show()
        assertThat(out.toString()).isEqualTo(
            """
            ◻️◻️
            ◻️◻️
            """.trimIndent()
        )
    }

    @Test
    internal fun `폭탄을 출력한다`() {
        val out = StringWriter()
        BoardView(Cells(listOf(Cell(), Cell(bomb = true)), width = 2), out).show()
        assertThat(out.toString()).isEqualTo(
            """
            ◻️💣
            """.trimIndent()
        )
    }
}
