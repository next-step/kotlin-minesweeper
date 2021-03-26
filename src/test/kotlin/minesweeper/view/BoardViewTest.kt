package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.Cells
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringWriter

class BoardViewTest {
    private val out = StringWriter()

    @Test
    internal fun `숫자와 폭탄수가 표시된다`() {
        val cells = Cells(
            listOf(
                Cell(), Cell(count = 1), Cell(bomb = true), Cell(bomb = true),
                Cell(), Cell(count = 1), Cell(count = 2), Cell(count = 2),
                Cell(), Cell(count = 0), Cell(count = 0), Cell(count = 0)
            ),
            width = 4
        ).apply {
            allOpen()
        }

        BoardView(cells, out).show()

        out showed
            """
                | 0 1💣💣
                | 0 1 2 2
                | 0 0 0 0
            """
    }

    @Test
    internal fun `가려져 있다`() {
        BoardView(
            Cells(listOf(Cell(), Cell(bomb = true)), width = 2), out
        ).show()

        out showed
            """
                |⬜⬜
            """
    }
}

private infix fun StringWriter.showed(content: String) {
    assertThat(toString()).isEqualTo(content.trimMargin())
}
