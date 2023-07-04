package minesweeper.domain.cell

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Coordinate.Companion.isContains

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

    context("right") {
        test("column위치가 오른쪽으로 이동한다.") {
            val actual = Coordinate(1, 1).right()
            actual shouldBe Coordinate(1, 2)
        }
    }

    context("left") {
        test("column위치가 왼쪽으로 이동한다.") {
            val actual = Coordinate(1, 1).left()
            actual shouldBe Coordinate(1, 0)
        }
    }

    context("isContains") {
        forAll(
            row(Cell(0, 0), true),
            row(Cell(1, 1), false),
        ) { input, expected ->
            test("cell이 해당 coordinates에 포함되는지 확인한다.") {
                val coordinates = listOf(Coordinate(0,0), Coordinate(0,1))
                val actual = coordinates.isContains(input)

                actual shouldBe expected
            }
        }
    }
})
