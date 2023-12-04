package domain.field

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import minesweeper.domain.MineCount
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.cell.CellMark
import minesweeper.domain.field
import minesweeper.domain.field.FieldSize
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width

class FieldBuilderTest : DescribeSpec({
    describe("field 생성") {
        context("높이(6)와 너비(4), 지뢰 개수(3)가 주어지면") {
            val size = FieldSize(Height(6), Width(4))
            val count = MineCount(3)

            val result = field(size, RandomPositionPicker()) {
                installMines(count)
            }

            it("셀의 총 개수는 높이(6) 과 너비(4)의 곱(24)과 같다") {
                result.cells.size shouldBe 24
            }

            it("cell 은 행(0부터 5(높이(6)) * 열(0부터 3(너비(4)))에 대한 위치 값을 가진다") {
                val expect = (0..5).flatMap { row -> (0..3).map { col -> row to col } }.toSet()

                result.cells.forEach {
                    it.position.row to it.position.column shouldBeIn expect
                }
            }

            it("지뢰 개수는 입력 받은 지뢰 개수(3)와 같다") {
                result.cells.count { it.mark == CellMark.MINE } shouldBe count.value
            }
        }

        context("지뢰 개수가 주어지지 않으면") {
            val size = FieldSize(Height(6), Width(4))

            it("필드 생성에 실패한다") {
                shouldThrowExactly<UninitializedPropertyAccessException> {
                    field(size, RandomPositionPicker()) {}
                }
            }
        }
    }
})
