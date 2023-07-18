package step4.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CoordinateTest : FunSpec({

    context("up") {
        test("row가 한칸 위로 올라간다.") {
            val givenCoordinate = Coordinate(1, 1)
            val expected = Coordinate(0, 1)

            val actual = givenCoordinate.up()
            actual shouldBe expected
        }
    }

    context("down") {
        test("row가 한칸 내려간다.") {
            val givenCoordinate = Coordinate(1, 1)
            val expected = Coordinate(2, 1)

            val actual = givenCoordinate.down()
            actual shouldBe expected
        }
    }

    context("right") {
        test("column이 한칸 오른쪽으로 간다.") {
            val givenCoordinate = Coordinate(1, 1)
            val expected = Coordinate(1, 2)

            val actual = givenCoordinate.right()
            actual shouldBe expected
        }
    }
})
