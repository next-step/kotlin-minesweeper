package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BoardTest : StringSpec({
    "보드에 지뢰개수만큼 지뢰가 생긴다." {
        val row = Row(3)
        val column = Column(3)
        val mineCount = MineCount(4)

        val board = Board.from(row, column, mineCount)

        board.cells.filterIsInstance<Mine>().size shouldBe mineCount.value
    }
})
