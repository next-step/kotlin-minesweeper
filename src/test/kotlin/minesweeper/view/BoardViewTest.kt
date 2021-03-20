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
}

private infix fun StringWriter.showed(content: String) {
    assertThat(toString()).isEqualTo(content.trimIndent())
}
