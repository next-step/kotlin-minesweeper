package domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.Height
import minesweeper.domain.board.MineBoardSize
import minesweeper.domain.board.Width
import minesweeper.domain.position.Position

class MineBoardSizeTest : DescribeSpec({
    describe("allPositionsOfRowAndColumns") {
        context("높이(3)와 너비(2)") {
            val size = MineBoardSize(Height(3), Width(2))

            it("행(0..2)과 열(0..1)에 대한 Position 생성") {
                val positions = size.allPositions

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
