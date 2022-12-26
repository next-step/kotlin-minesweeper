package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PointTest {

    @Test
    fun `좌표값 x는 음수가 될 수 없다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Point(-1, 1)
        }

        assertThat(exception.message).isEqualTo("좌표 값은 음수가 될 수 없습니다.")
    }

    @Test
    fun `좌표값 y는 음수가 될 수 없다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Point(-1, 1)
        }

        assertThat(exception.message).isEqualTo("좌표 값은 음수가 될 수 없습니다.")
    }

    @Test
    fun `Point 는 y 기준 오름차순으로 정렬된다`() {
        val points = listOf(Point(0, 3), Point(0, 1), Point(0, 2))
        val sortedPoints = points.sorted()

        assertThat(sortedPoints).isEqualTo(listOf(Point(0, 1), Point(0, 2), Point(0, 3)))
    }

    @Test
    fun `Point 는 y가 같을 땐 x 기준 오름차순 정렬된다`() {
        val points = listOf(Point(1, 0), Point(0, 0), Point(2, 0))
        val sortedPoints = points.sorted()

        assertThat(sortedPoints).isEqualTo(listOf(Point(0, 0), Point(1, 0), Point(2, 0)))
    }
}
