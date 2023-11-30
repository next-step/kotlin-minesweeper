package domain.field

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import minesweeper.domain.field.Field
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width

class FieldTest : DescribeSpec({
    describe("Field.of()") {
        context("주어진 높이(6)와 너비(4)") {
            val height = Height(6)
            val width = Width(4)

            val field = Field.of(height, width)

            it("생성된 필드의 높이: 6") {
                field.size.height shouldBe height
            }

            it("생성된 필드의 너비: 4") {
                field.size.width shouldBe width
            }

            it("셀의 총 개수는 높이(6) 과 너비(4)의 곱(24)") {
                field.elements.size shouldBe 24
            }

            it("행(0부터 5(높이(6)) * 열(0부터 3(너비(4)))에 대한 위치 값을 가진 cell") {
                val expect = (0..5).flatMap { row -> (0..3).map { col -> row to col } }.toSet()

                field.elements.forEach {
                    it.position.row to it.position.column shouldBeIn expect
                }
                field.elements.size shouldBe expect.size
            }
        }
    }
})
