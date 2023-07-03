package minesweeper.domain

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PointTest: ShouldSpec({
    should("x, y가 같은 포인트는 동일 하다.") {
        Point(1, 1) shouldBe Point(1, 1)
    }

    should("포인트는 y, x 오름차순으로 정렬 된다.") {
        val points = listOf(
            Point(1, 1), Point(2, 1),
            Point(1, 2), Point(2, 2),
        )

        val sortedPoints = points.shuffled().sorted()

        sortedPoints[0] shouldBe Point(1, 1)
        sortedPoints[1] shouldBe Point(2, 1)
        sortedPoints[2] shouldBe Point(1, 2)
        sortedPoints[3] shouldBe Point(2, 2)
    }

    should("높이와 너비를 입력받아 사각형에 해당하는 포인트를 반환 한다.") {
        val points = Point.square(2, 2)
        points[0] shouldBe  Point(0, 0)
        points[1] shouldBe  Point(1, 0)
        points[2] shouldBe  Point(0, 1)
        points[3] shouldBe  Point(1, 1)
    }
})
