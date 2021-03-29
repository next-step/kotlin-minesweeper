package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LinkTest {
    @Test
    internal fun `인덱스를 통해 연결된 셀을 제공한다`() {
        val leftTop = CellLegacy()
        val linked = arrayOf(CellLegacy(), CellLegacy(), CellLegacy())

        val link = Link(Matrix(2, 2), listOf(leftTop, *linked))

        assertThat(link.cells(1))
            .hasSize(3)
            .contains(*linked)
    }
}
