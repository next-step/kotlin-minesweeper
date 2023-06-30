package next.step.minesweeper.domain.mine

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.board.BoardHeight
import next.step.minesweeper.domain.board.BoardWidth
import next.step.minesweeper.domain.point.Point
import org.junit.jupiter.api.assertThrows

class MineBoardTest : DescribeSpec({

    describe("MineBoard") {
        val board = Board.of(BoardHeight.of(10), BoardWidth.of(10))
        context("생성") {
            it("보드 크기보다 지뢰 개수가 더 많으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of((1..101).map { Point.of(it, 0) }.toSet()))
                }.shouldHaveMessage("지뢰 개수는 100개보다 많을 수 없습니다.")
            }

            it("지뢰 위치 y가 보드 최대 높이 이상이면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of(setOf(Point.of(0, 10))))
                }.shouldHaveMessage("지뢰 y 위치는 10 보다 작아야 합니다.")
            }

            it("지뢰 위치 x가 보드 최대 너비 이상이면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of(setOf(Point.of(10, 0))))
                }.shouldHaveMessage("지뢰 x 위치는 10 보다 작아야 합니다.")
            }

            it("지뢰 위치 y가 0보다 작으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of(setOf(Point.of(0, -1))))
                }.shouldHaveMessage("지뢰 y 위치는 0 보다 작을 수 없습니다.")
            }

            it("지뢰 위치 x가 0보다 작으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MineBoard.of(board, MinePoints.of(setOf(Point.of(-1, 0))))
                }.shouldHaveMessage("지뢰 x 위치는 0 보다 작을 수 없습니다.")
            }
        }

        context("method") {
            val mineBoard = MineBoard.of(board, MinePoints.of(setOf(Point.of(0, 0))))

            it("지뢰가 있는 위치이면 true") {
                mineBoard.isMineAt(Point.of(0, 0)) shouldBe true
            }
            it("지뢰가 없는 위치이면 false") {
                mineBoard.isMineAt(Point.of(1, 0)) shouldBe false
            }
            it("0점 제공") {
                mineBoard.zero() shouldBe Point.of(0, 0)
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
