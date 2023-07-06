package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellsSpec : DescribeSpec({
    describe("사이즈 검증") {
        context("3개의 cell 을 갖고 있다면") {
            val cells = Cells(
                listOf(
                    Normal(0, 0),
                    Normal(1, 0),
                    Normal(2, 0),
                ),
            )
            it("3개 포함 확인 결과는 '참'이다.") {
                cells.hasSize(3) shouldBe true
            }
            it("4개 포함 확인 결과는 '거짓'이다.") {
                cells.hasSize(4) shouldBe false
            }
        }
    }
})
