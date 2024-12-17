package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.Board


class BoardTest: StringSpec({
    "지뢰찾기 보드 생성 테스트 1" {
        val board = Board(3, 3, 1)
        board.createCells().size shouldBe 9
    }
    "지뢰찾기 보드 생성 테스트 2" {
        val board = Board(4, 3, 1)
        board.createCells().size shouldBe 12
    }
})
