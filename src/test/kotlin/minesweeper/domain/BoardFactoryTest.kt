package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardFactoryTest {
    @Test
    internal fun `높이 너비만큼 셀을 만든다`() {
        assertThat(BoardFactory(10, 10).board(1).cellCount).isEqualTo(100)
    }

    @Test
    internal fun `지뢰 개수만큼 생성한다`() {
        val cells = BoardFactory(10, 10).board(bomb = 10).cells
        assertThat(cells.filter { it.bomb }).hasSize(10)
    }

    @Test
    internal fun `지뢰는 최소 1개이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { BoardFactory(10, 10).board(bomb = 0) }
    }

    @Test
    internal fun `너비와 높이는 1 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { BoardFactory(0, 10).board(bomb = 0) }
    }

    @Test
    internal fun `지뢰수는 전체 셀수보다 작아야 한다`() {
        assertThrows<IllegalArgumentException> { BoardFactory(2, 2).board(bomb = 4) }
    }
}
