package domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Height
import minesweeper.domain.MineField
import minesweeper.domain.Width

class MineFieldTest : DescribeSpec({
    describe("MineField()") {
        context("주어진 높이(5)와 너비(5)") {
            val height = Height(5)
            val width = Width(5)

            it("생성된 필드의 높이: 5, 너비: 5") {
                val field = MineField(height, width)

                field.height shouldBe height
                field.width shouldBe width
            }
        }
    }
})
