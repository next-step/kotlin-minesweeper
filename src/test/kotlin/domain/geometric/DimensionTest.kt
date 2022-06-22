package domain.geometric

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll

class DimensionTest : DescribeSpec({

    describe("차원은") {
        it("0보다 큰 값으로 표현되는 너비(width)와 높이(height)를 가진다") {
            shouldNotThrowAny { Dimension(10, 10) }
        }
        context("너비(width)가 0보다 같거나 작다면") {
            it("예외를 발생시킨다") {
                listOf(0, -1).forAll { width ->
                    shouldThrowExactly<IllegalArgumentException> { Dimension(width = width, height = 10) }
                }
            }
        }
        context("높이(height)가 0보다 같거나 작다면") {
            it("예외를 발생시킨다") {
                listOf(0, -1).forAll { height ->
                    shouldThrowExactly<IllegalArgumentException> { Dimension(width = 10, height = height) }
                }
            }
        }
    }
})
