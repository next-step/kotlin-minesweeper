package domain.field

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.field
import minesweeper.domain.field.FieldSize
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width

class FieldBuilderTest : DescribeSpec({
    xdescribe("field 생성") {
        context("주어진 높이(6)와 너비(4)") {
            val size = FieldSize(Height(6), Width(4))

            val result = field(size, RandomPositionPicker()) {}

            it("셀의 총 개수는 높이(6) 과 너비(4)의 곱(24)") {
                result.cells.size shouldBe 24
            }

            it("행(0부터 5(높이(6)) * 열(0부터 3(너비(4)))에 대한 위치 값을 가진 cell") {
                val expect = (0..5).flatMap { row -> (0..3).map { col -> row to col } }.toSet()

                result.cells.forEach {
                    it.position.row to it.position.column shouldBeIn expect
                }
            }
        }
    }
})
