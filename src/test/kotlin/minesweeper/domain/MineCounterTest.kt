package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.view.BoardStatus

class MineCounterTest : StringSpec({

    "지뢰가 없는 보드판은 모든 칸의 지뢰 개수가 0이다." {
        val board = BoardGenerator.create(BoardStatus(5, 5, 0))
        board.getBoardInfo.sumOf { row -> row.rowInfo.count { cell -> cell.mineCount == 0 } } shouldBe 25
    }
})
