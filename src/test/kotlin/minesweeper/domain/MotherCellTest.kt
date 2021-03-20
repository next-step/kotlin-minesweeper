package minesweeper.domain

import org.junit.jupiter.api.Test

class MotherCellTest {
    @Test
    internal fun `높이 너비만큼 샐을 만든다`() {
        assertThat(MotherCell(10, 10).cells).hasSize(100)
    }
}
