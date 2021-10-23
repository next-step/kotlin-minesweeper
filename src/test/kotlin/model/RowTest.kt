package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RowTest {
    private val plainRow = Row(
        Cell(CellType.Plain, Position(0, 1, 5)),
        Cell(CellType.Plain, Position(1, 1, 5))
    )
    private val mineRow = Row(
        Cell(CellType.Mine, Position(0, 0, 5)),
        Cell(CellType.Plain, Position(1, 0, 5))
    )

    @Test
    fun `mineCell 을 open 하면 containingExploded true`() {
        assertThat(plainRow.containingExploded()).isFalse()
        assertThat(mineRow.containingExploded()).isFalse()

        mineRow.get(0).open()
        assertThat(mineRow.containingExploded()).isTrue()
    }

    @Test
    fun `clean`() {
        // tc 1
        assertThat(plainRow.clean()).isFalse()

        plainRow.get(0).open()
        assertThat(plainRow.clean()).isFalse()

        plainRow.get(1).open()
        assertThat(plainRow.clean()).isTrue()

        // tc 2
        assertThat(mineRow.clean()).isFalse()

        mineRow.get(1).open()
        assertThat(mineRow.clean()).isTrue()

        mineRow.get(0).open()
        assertThat(mineRow.clean()).isFalse()
    }
}