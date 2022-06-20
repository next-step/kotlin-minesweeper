package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Board
import minesweeper.domain.BoardSize

class BoardTest : DescribeSpec({

    describe("generate") {
        context("높이와 너비가 주어지면") {
            it("해당 크기의 보드를 생성한다.") {
                val board = Board.generate(BoardSize(10, 20))
                board.board.size shouldBe 10
                board.board[0].size shouldBe 20
            }
        }
    }
})
