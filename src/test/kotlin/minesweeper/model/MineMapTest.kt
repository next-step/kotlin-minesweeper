package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineMapTest {
    @Test
    internal fun `높이와 너비는 각각 행크기, 열크기가 된다`() {
        val height = 10
        val width = 10
        val mineMap = MineMap.of(height, width)
        assertThat(mineMap.rowSize).isEqualTo(height)
        assertThat(mineMap.columnSize).isEqualTo(width)
    }

    @ParameterizedTest
    @CsvSource(
        value = ["0:0", "1:0", "0:1", "-1:0", "0:-1", "-1:-1"], delimiter = ':'
    )
    internal fun `크기가 0보다 작은 지뢰판은 생성할 수 없다`(height: Int, width: Int) {
        assertThrows<IllegalArgumentException> { MineMap.of(height, width) }
    }
}
