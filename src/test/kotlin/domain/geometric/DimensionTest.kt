package domain.geometric

import domain.location
import domain.locations
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe

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

        it("차원의 넓이만큼 위치를 채울 수 있다") {
            Dimension(2, 2).fill() shouldContainInOrder listOf(
                location(0, 0), location(0, 1),
                location(1, 0), location(1, 1),
            )
        }

        context("여러 위치가 주어지면") {
            val table = io.kotest.data.table(
                headers("locations", "result"),
                row(locations(0 to 0, 0 to 1, 1 to 1, 1 to 0), true),
                row(locations(0 to 0, 0 to 1, 1 to 1, 1 to 2), false),
                row(locations(0 to 0, 0 to 1, 1 to 1, 1 to 1), false),
            )
            val dimension = Dimension(2, 2)
            it("차원을 채울 수 있는지 판단할 수 있다") {
                table.forAll { locations, result ->
                    dimension.isFilled(locations) shouldBe result
                }
            }
        }
    }
})
