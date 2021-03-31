package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EmptyCellTest {

    @Test
    fun `expose하면 주변 마인의 수를 노출 한다`() {
        val cell = EmptyCell()
        cell.expose(listOf(MineCell(), MineCell(), EmptyCell()))
        assertThat(cell.aroundMineCount).isEqualTo(2)
    }
}
