package minesweeper

import minesweeper.domain.MineMapConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.lang.IllegalArgumentException

internal class MineMapConfigTest {
    @ParameterizedTest
    @CsvSource(
        "0, 1, 1",
        "1, 0, 1",
        "1, 1, 0",
    )
    internal fun `높이와 너비 그리고 지뢰 개수가 0보다 작으면 예외가 발생한다`(
        height: Int,
        width: Int,
        mineCount: Int,
    ) {
        assertThrows<IllegalArgumentException> { MineMapConfig(height, width, mineCount) }
    }

    @Test
    internal fun `지뢰 개수가 높이와 너비의 곱보다 크면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { MineMapConfig(10, 10, 101) }
    }
}
