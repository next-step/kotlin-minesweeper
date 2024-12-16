package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardTest : DescribeSpec({
    describe("지뢰찾기 보드를 생성한다.") {
        context("높이가 1보다 작은경우") {
            it("throw exception") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Board(0, 10, 10)
                    }
                exception.message shouldBe "높이는 1이상입니다."
            }
        }

        context("너비가 1보다 작은경우") {
            it("throw exception") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Board(10, 0, 10)
                    }
                exception.message shouldBe "너비는 1이상입니다."
            }
        }

        context("전체 보드의 크기보다 지뢰가 많은경우") {
            it("throw exception") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Board(2, 2, 5)
                    }
                exception.message shouldBe "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
            }
        }
    }
})
