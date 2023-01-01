package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineMapTest {

    @Test
    fun `입력한 높이와 너비의 곱셈 값 만큼 셀이 만들어진다`() {
        // given
        val height = 10
        val width = 3
        val mineCount = 2

        val mineMap = MineMap(height, width, mineCount)

        // when
        val actual = mineMap.generate().size

        // then
        val expected = height * width
        assertThat(actual).isEqualTo(expected)
    }
}
