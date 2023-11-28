package minesweeper

import org.assertj.core.api.Java6Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class MineMapInfoTest {
    @Test
    fun `맵의 행 길이는 양수여야한다`() {
        assertThatThrownBy {
            MineMapInfo(LineCount(0), LineCount(5), MineCount(5))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `맵의 열 길이는 양수여야한다`() {
        assertThatThrownBy {
            MineMapInfo(LineCount(5), LineCount(0), MineCount(5))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `지뢰 개수는 양수여야한다`() {
        assertThatThrownBy {
            MineMapInfo(LineCount(5), LineCount(5), MineCount(0))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `지뢰 개수는 맵의 행열을 곱한 것보다 많으면 안된다`() {
        assertDoesNotThrow {
            MineMapInfo(LineCount(10), LineCount(10), MineCount(100))
        }

        assertThatThrownBy {
            MineMapInfo(LineCount(10), LineCount(10), MineCount(101))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
