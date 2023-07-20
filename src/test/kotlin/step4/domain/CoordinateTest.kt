package step4.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class CoordinateTest : FunSpec({

    context("init") {
        test("row가 0보다 작은 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Coordinate(-1, 0) }
            exception shouldHaveMessage "row는 0미만의 값이 입력될 수 없습니다."
        }

        test("column이 0보다 작은 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Coordinate(0, -1) }
            exception shouldHaveMessage "column은 0미만의 값이 입력될 수 없습니다."
        }
    }

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

    context("left") {
        test("column이 한칸 왼쪽으로 간다.") {
            val givenCoordinate = Coordinate(1, 1)
            val expected = Coordinate(1, 0)

            val actual = givenCoordinate.left()
            actual shouldBe expected
        }
    }
})
