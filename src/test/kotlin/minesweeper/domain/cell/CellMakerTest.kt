package minesweeper.domain.cell

import minesweeper.domain.cell.Cell.Empty
import minesweeper.domain.cell.Cell.Mine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CellMakerTest {

    @Test
    fun `CellMaker 생성 테스트`() {
        // given
        val width = 2
        val height = 3
        val numberOfMines = 2

        // when
        val cells = CellMaker.make(width, height, numberOfMines)

        // then
        assertThat(cells.count { it is Mine }).isEqualTo(numberOfMines)
        assertThat(cells.count { it is Empty }).isEqualTo(width * height - numberOfMines)
    }
}
