package next.step.minesweeper.domain.mine

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.position.Position
import org.junit.jupiter.api.assertThrows

class MineBoardTest : DescribeSpec({

    describe("MineBoard") {
        val board = Board.of(10, 10)
        context("생성") {
            it("보드 크기보다 지뢰 개수가 더 많으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of((1..101).map { Position(it, 0) }.toSet()))
                }.shouldHaveMessage("지뢰 개수는 100개보다 많을 수 없습니다.")
            }

            it("지뢰 위치 y가 보드 최대 높이 이상이면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of(setOf(Position(0, 10))))
                }.shouldHaveMessage("지뢰 y 위치는 10 보다 작아야 합니다.")
            }

            it("지뢰 위치 x가 보드 최대 너비 이상이면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of(setOf(Position(10, 0))))
                }.shouldHaveMessage("지뢰 x 위치는 10 보다 작아야 합니다.")
            }
        }

        context("method") {
            val mineBoard = MineBoard.of(board, MinePoints.of(setOf(Position(0, 0))))

            it("지뢰가 있는 위치이면 true") {
                mineBoard.isMineAt(Position(0, 0)) shouldBe true
            }
            it("지뢰가 없는 위치이면 false") {
                mineBoard.isMineAt(Position(1, 0)) shouldBe false
            }
            it("높이 제공") {
                mineBoard.height() shouldBe 10
            }
            it("너비 제공") {
                mineBoard.width() shouldBe 10
            }
        }
    }
})
