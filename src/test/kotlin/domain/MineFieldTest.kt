package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import minesweeper.domain.Height
import minesweeper.domain.MineField
import minesweeper.domain.Width

class MineFieldTest : DescribeSpec({
    describe("MineField.of()") {
        context("주어진 높이(6)와 너비(4)") {
            val height = Height(6)
            val width = Width(4)

            val field = MineField.of(height, width)

            it("생성된 필드의 높이: 6") {
                field.height shouldBe height
            }

            it("생성된 필드의 너비: 4") {
                field.width shouldBe width
            }

            it("셀의 총 개수는 높이(6) 과 너비(4)의 곱(24)") {
                field.elements.size shouldBe 24
            }

            it("행(0부터 5(높이(6)) * 열(0부터 3(너비(4)))에 대한 위치 값을 가진 cell") {
                val expect = (0..5).flatMap { row -> (0..3).map { col -> row to col } }.toSet()

                field.elements.forEach {
                    it.row to it.column shouldBeIn expect
                }
                field.elements.size shouldBe expect.size
            }
        }
    }
})
