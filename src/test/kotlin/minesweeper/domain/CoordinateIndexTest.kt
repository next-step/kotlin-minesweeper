package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class CoordinateIndexTest : DescribeSpec({

    describe("constructor") {
        context("0 이상의 숫자가 주어지면") {
            it("좌표 값을 생성한다") {
                CoordinateIndex(0) shouldNotBe null
            }
        }

        context("0 미만의 숫자가 주어지면") {
            it("IllegalArgumentException 이 발생한다") {
                shouldThrow<IllegalArgumentException> { CoordinateIndex(-1) }
            }
        }
    }
})
