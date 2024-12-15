package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellTypeTest {
    @Test
    fun `지뢰 타입이 존재한다`() {
        assertThat(CellType.MINE.isMine()).isTrue()
    }
}