package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PointTest {

    @Test
    fun `좌표값 x는 음수가 될 수 없다`() {
        val exception = assertThrows<MineSweeperException> {
            Point(-1, 1)
        }

        assertThat(exception.reason).isEqualTo(ExceptionReason.NEGATIVE_POINT_VALUE)
    }

    @Test
    fun `좌표값 y는 음수가 될 수 없다`() {
        val exception = assertThrows<MineSweeperException> {
            Point(-1, 1)
        }

        assertThat(exception.reason).isEqualTo(ExceptionReason.NEGATIVE_POINT_VALUE)
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

    @Test
    fun `꼭짓점 블록은 3개의 주변 블록을 가진다`() {
        val point = Point(0, 0)

        assertThat(point.getNearPoints(3, 3).size).isEqualTo(3)
    }

    @Test
    fun `모서리 블록은 5개의 주변 블록을 가진다`() {
        val point = Point(1, 0)

        assertThat(point.getNearPoints(3, 3).size).isEqualTo(5)
    }

    @Test
    fun `완전히 둘러쌓인 블록은 8개의 주변 블록을 가진다`() {
        val point = Point(1, 1)

        assertThat(point.getNearPoints(3, 3).size).isEqualTo(8)
    }
}
