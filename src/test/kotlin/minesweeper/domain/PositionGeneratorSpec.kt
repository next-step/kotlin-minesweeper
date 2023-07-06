package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class PositionGeneratorSpec : DescribeSpec({
    describe("랜덤 위치 생성 검증") {
        context("너비, 높이, 생성 개수가 주어지면") {
            it("랜덤 위치를 생성할 수 있다.") {
                val width = PositiveInt(3)
                val height = PositiveInt(3)
                val count = PositiveInt(3)

                val positions = PositionGenerator.generatePositionsRandomly(width, height, count)

                positions.size shouldBe 3
                positions.forAll {
                    it.x shouldBeInRange 0..2
                    it.y shouldBeInRange 0..2
                }
            }
        }
    }
})
