package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PointTest : StringSpec({

    "자기 주변의 8방향의 격자점을 반환한다." {
        val point = Point(2, 2)

        val expectedPoints = setOf(
            Point(1, 1), Point(1, 2), Point(1, 3),
            Point(2, 1), Point(2, 3),
            Point(3, 1), Point(3, 2), Point(3, 3)
        )

        val adjacentPoints = point.getAdjacentPoints()

        adjacentPoints shouldBe expectedPoints
    }
})
