package minesweeper.model.point

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.point.CoordinateFixture.toCoordinate

class CoordinateTest : StringSpec({

    "변위(delta) 만큼 이동된 위치의 Coordinate 객체가 만들어져야만 한다" {
        val coordinate = (0 to 0).toCoordinate()
        val actual = coordinate.moveTo(Delta(3, 5))

        actual shouldBe (3 to 5).toCoordinate()
    }

    "범위 밖 위치로 이동이 불가능 해야 한다" {
        val coordinate = (3 to 3).toCoordinate()

        coordinate.movePossible(
            delta = Delta(3, 3),
            verticalLimit = 5,
            horizontalLimit = 5
        ) shouldBe false
    }

    "범위 밖 음수 영역 으로는 이동이 불가능 해야 한다" {
        val coordinate = (3 to 3).toCoordinate()

        coordinate.movePossible(
            delta = Delta(-4, -3),
            verticalLimit = 5,
            horizontalLimit = 5
        ) shouldBe false
    }

    "범위 내 위치로 이동이 가능 해야 한다" {
        val coordinate = (3 to 3).toCoordinate()

        coordinate.movePossible(
            delta = Delta(2, 2),
            verticalLimit = 5,
            horizontalLimit = 5
        ) shouldBe true

        coordinate.movePossible(
            delta = Delta(-2, -2),
            verticalLimit = 5,
            horizontalLimit = 5
        ) shouldBe true
    }
})
