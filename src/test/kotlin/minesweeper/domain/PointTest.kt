package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PointTest : ShouldSpec({
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
        points[0] shouldBe Point(0, 0)
        points[1] shouldBe Point(1, 0)
        points[2] shouldBe Point(0, 1)
        points[3] shouldBe Point(1, 1)
    }

    should("y값이 0보다 작은 포인트를 생성할 수 없다.") {
        shouldThrow<IllegalArgumentException> { Point(0, -1) }
    }

    should("0, 0에서 아래왼쪽, 왼쪽, 위왼쪽, 위, 위오른쪽은 존재하지 않는다.") {
        listOf(Direction.DOWN_LEFT, Direction.LEFT, Direction.UP_LEFT, Direction.UP, Direction.UP_RIGHT)
            .forEach {
                shouldThrow<IllegalArgumentException> { Point(0, 0).nextPoint(it) }
            }
    }

    should("1, 1에서 주변 좌표는 모두 존재 한다.") {
        Direction.values().forEach {
            shouldNotThrow<IllegalArgumentException> { Point(1, 1).nextPoint(it) }
        }
    }
})
