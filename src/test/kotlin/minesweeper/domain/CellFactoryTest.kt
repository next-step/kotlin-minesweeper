package minesweeper.domain

import minesweeper.domain.BoardBuilder.Companion.`⬜`
import minesweeper.domain.BoardBuilder.Companion.`💣`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellFactoryTest {
    private val matrix = Matrix(4, 3)
    private val cells = CellFactory.Default(
        RandomDoubles(
            listOf(
                `⬜`, `⬜`, `💣`, `💣`,
                `⬜`, `⬜`, `⬜`, `⬜`,
                `⬜`, `⬜`, `⬜`, `⬜`
            ).map { it.toDouble() }
        )
    ).cells(2, matrix)

    @Test
    fun `옆 셀의 지뢰수가 기록되어 있다`() {
        assertThat(cells).hasSize(12)

        // first line
        assertThat(bombCount(0, 0)).isEqualTo(0)
        assertThat(bombCount(1, 0)).isEqualTo(1)
        assertThat(bomb(2, 0)).isTrue()
        assertThat(bomb(3, 0)).isTrue()

        // second line
        assertThat(bombCount(0, 1)).isEqualTo(0)
        assertThat(bombCount(1, 1)).isEqualTo(1)
        assertThat(bombCount(2, 1)).isEqualTo(2)
        assertThat(bombCount(3, 1)).isEqualTo(2)

        // third line
        repeat(3) {
            assertThat(bombCount(it, 2)).isEqualTo(0)
        }
    }

    private fun bombCount(x: Int, y: Int) = cells[matrix.toIndex(Position(x, y))].count
    private fun bomb(x: Int, y: Int) = cells[matrix.toIndex(Position(x, y))].bomb
}
