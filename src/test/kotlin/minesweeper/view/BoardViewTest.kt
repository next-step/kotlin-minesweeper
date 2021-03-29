package minesweeper.view

import minesweeper.domain.CellLegacy
import minesweeper.domain.Board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringWriter

class BoardViewTest {
    private val out = StringWriter()

    @Test
    internal fun `숫자와 폭탄수가 표시된다`() {
        val cells = Board(
            listOf(
                CellLegacy(), CellLegacy(count = 1), CellLegacy(bomb = true), CellLegacy(bomb = true),
                CellLegacy(), CellLegacy(count = 1), CellLegacy(count = 2), CellLegacy(count = 2),
                CellLegacy(), CellLegacy(count = 0), CellLegacy(count = 0), CellLegacy(count = 0)
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
            Board(listOf(CellLegacy(), CellLegacy(bomb = true)), width = 2), out
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
