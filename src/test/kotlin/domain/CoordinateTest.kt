package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CoordinateTest : FunSpec({

    test("좌표를 더한다") {
        Coordinate(1, 2) + Coordinate(3, 4) shouldBe Coordinate(4, 6)
    }

    test("한 칸 위쪽 좌표를 반환한다") {
        Coordinate.origin().up() shouldBe Coordinate(-1, 0)
    }

    test("한 칸 아래쪽 좌표를 반환한다") {
        Coordinate.origin().down() shouldBe Coordinate(1, 0)
    }

    test("한 칸 왼쪽 좌표를 반환한다") {
        Coordinate.origin().left() shouldBe Coordinate(0, -1)
    }

    test("한 칸 오른쪽 좌표를 반환한다") {
        Coordinate.origin().right() shouldBe Coordinate(0, 1)
    }

    context("지뢰찾기 보드 위에 좌표가 있는지 반환한다") {
        data class CoordinateIsOnBoard(val coordinate: Coordinate, val expected: Boolean)
        withData(
            CoordinateIsOnBoard(Coordinate(0, 0), true),
            CoordinateIsOnBoard(Coordinate(-1, 0), false),
            CoordinateIsOnBoard(Coordinate(0, -1), false),
            CoordinateIsOnBoard(Coordinate(2, 2), true),
            CoordinateIsOnBoard(Coordinate(3, 0), false),
            CoordinateIsOnBoard(Coordinate(0, 3), false),
        ) { (coordinate, expected) ->
            coordinate.isOnBoard(height = 3, width = 3) shouldBe expected
        }
    }

    test("원점을 반환한다") {
        Coordinate.origin() shouldBe Coordinate(0, 0)
    }
})
