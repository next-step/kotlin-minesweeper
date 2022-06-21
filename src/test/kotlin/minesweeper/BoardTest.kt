package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Board
import minesweeper.domain.BoardSize
import minesweeper.domain.MineCount

class BoardTest : DescribeSpec({

    describe("generate") {
        context("높이와 너비가 주어지면") {
            it("해당 크기의 보드를 생성한다.") {
                val board = Board.generate(BoardSize(10, 20), MineCount(1))
                board.board.size shouldBe 10
                board.board[0].size shouldBe 20
            }
        }

        context("크기보다 지뢰의 개수가 많으면") {
            it("Invalid 상태를 반환한다.") {
                shouldThrow<IllegalArgumentException> {
                    Board.generate(BoardSize(1, 1), MineCount(10))
                }
            }
        }
    }
})
