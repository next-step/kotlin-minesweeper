package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CoordinatesTest : FunSpec({

    test("좌표의 주변 좌표를 모두 반환한다") {
        Coordinates.neighbors(Coordinate(1, 1)) shouldBe listOf(
            Coordinate(0, 0), Coordinate(0, 1), Coordinate(0, 2),
            Coordinate(1, 0), Coordinate(1, 2),
            Coordinate(2, 0), Coordinate(2, 1), Coordinate(2, 2),
        )
    }

    test("주어진 높이, 너비에 해당하는 보드의 좌표를 모두 반환한다") {
        Coordinates.all(height = 4, width = 3) shouldBe listOf(
            Coordinate(0, 0), Coordinate(0, 1), Coordinate(0, 2),
            Coordinate(1, 0), Coordinate(1, 1), Coordinate(1, 2),
            Coordinate(2, 0), Coordinate(2, 1), Coordinate(2, 2),
            Coordinate(3, 0), Coordinate(3, 1), Coordinate(3, 2),
        )
    }
})
