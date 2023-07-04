package minesweeper.domain.cell

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CoordinateTest : FunSpec({

    context("up") {
        test("row위치가 위로 올라간다.") {
            val actual = Coordinate(1, 1).up()
            actual shouldBe Coordinate(0, 1)
        }
    }

    context("down") {
        test("row위치가 아래로 내려간다.") {
            val actual = Coordinate(1, 1).down()
            actual shouldBe Coordinate(2, 1)
        }
    }
})
