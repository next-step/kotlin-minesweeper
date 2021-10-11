package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CellTypeTest {
    @Test
    fun `CellType 은 지뢰 cell , 안전 cell 2가지 존재한다`() {
        assertThat(CellType.values()).containsExactly(CellType.Mine, CellType.Plain)
    }
}