package minesweeper.view

import minesweeper.domain.BoardBuilder.Companion.`⬜`
import minesweeper.domain.BoardBuilder.Companion.`💣`
import minesweeper.domain.board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringWriter

class BoardViewTest {
    private val out = StringWriter()

    @Test
    internal fun `숫자와 폭탄수가 표시된다`() {
        val board = board {
            row(`⬜`, `⬜`, `⬜`, `💣`, `💣`)
            row(`⬜`, `⬜`, `⬜`, `⬜`, `⬜`)
            row(`💣`, `⬜`, `⬜`, `⬜`, `⬜`)
        }.build()
            .apply {
                allOpen()
            }

        BoardView(board, out).show()

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
            board {
                row(`⬜`, `💣`)
            }.build(),
            out
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
