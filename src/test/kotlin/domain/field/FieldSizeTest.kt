package domain.field

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Position
import minesweeper.domain.field.FieldSize
import minesweeper.domain.field.Height
import minesweeper.domain.field.Width

class FieldSizeTest : DescribeSpec({
    describe("allPositionsOfRowAndColumns") {
        context("높이(3)와 너비(2)") {
            val size = FieldSize(Height(3), Width(2))

            it("행(0..2)과 열(0..1)에 대한 Position 생성") {
                val positions = size.allPositionsOfRowAndColumns

                val expect = setOf(
                    Position(0, 0), Position(0, 1),
                    Position(1, 0), Position(1, 1),
                    Position(2, 0), Position(2, 1),
                )
                positions shouldBe expect
            }
        }
    }
})
