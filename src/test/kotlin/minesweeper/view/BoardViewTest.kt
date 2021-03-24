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
        val cells = listOf(
            Cell(), Cell(count = 1), Cell(bomb = true), Cell(bomb = true),
            Cell(), Cell(count = 1), Cell(count = 2), Cell(count = 2),
            Cell(), Cell(count = 0), Cell(count = 0), Cell(count = 0)
        )
        BoardView(Cells(cells, width = 4), out).show()

        out showed
            """
                | 0 1💣💣
                | 0 1 2 2
                | 0 0 0 0
            """
    }
}

private infix fun StringWriter.showed(content: String) {
    assertThat(toString()).isEqualTo(content.trimMargin())
}
