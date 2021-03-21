package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.Cells
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringWriter

class BoardViewTest {
    private val out = StringWriter()

    @Test
    internal fun `높이 너비로 화면에 출력한다`() {
        BoardView(Cells(listOf(Cell(), Cell(), Cell(), Cell(bomb = true)), width = 2), out).show()

        out showed
            """
                ◻️◻️
                ◻️💣
            """
    }

    @Test
    internal fun `숫자가 표시된다`() {
        val cells = listOf(
            Cell(), Cell(count = 1), Cell(bomb = true), Cell(bomb = true),
            Cell(), Cell(count = 1), Cell(count = 2), Cell(count = 2),
            Cell(), Cell(count = 0), Cell(count = 0), Cell(count = 0)
        )
        BoardView(Cells(cells, width = 4), out).show()

        out showed
            """
                ◻️1💣💣
                ◻️122
                ◻️◻️◻️◻️
            """
    }
}

private infix fun StringWriter.showed(content: String) {
    assertThat(toString()).isEqualTo(content.trimIndent())
}
