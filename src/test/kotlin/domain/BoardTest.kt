package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BoardTest : FunSpec({
    test("addCell() 테스트") {
        // given
        val board = Board(3, 3, 1)
        val cell = Cell.create(1, 1)

        // when
        board.addCell(cell)

        // then
        board.cells.size shouldBe 1
    }
})
