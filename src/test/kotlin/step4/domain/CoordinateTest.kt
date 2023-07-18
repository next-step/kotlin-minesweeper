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
})
