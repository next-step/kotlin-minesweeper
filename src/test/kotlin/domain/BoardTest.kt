package domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import strategy.FixedMineCellGenerator

class BoardTest : DescribeSpec({
    describe("지뢰찾기 보드를 생성한다.") {
        context("높이가 1보다 작은경우") {
            it("throw exception") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Board(Coordinate(BoardHeight(0), BoardWidth(10)), MineCount(10), FixedMineCellGenerator())
                    }
                exception.message shouldBe "높이는 1이상입니다."
            }
        }

        context("너비가 1보다 작은경우") {
            it("throw exception") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Board(Coordinate(BoardHeight(10), BoardWidth(0)), MineCount(10), FixedMineCellGenerator())
                    }
                exception.message shouldBe "너비는 1이상입니다."
            }
        }

        context("전체 보드의 크기보다 지뢰가 많은경우") {
            it("throw exception") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Board(Coordinate(BoardHeight(2), BoardWidth(2)), MineCount(5), FixedMineCellGenerator())
                    }
                exception.message shouldBe "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
            }
        }

        context("높이와 너비가 1 이상이고, 전체 셀보다 지뢰가 적은경우") {
            it("should not throw exception") {
                shouldNotThrow<IllegalArgumentException> {
                    Board(Coordinate(BoardHeight(2), BoardWidth(2)), MineCount(3), FixedMineCellGenerator())
                }
            }
        }
    }

    describe("지뢰찾기 보드를 생성한다") {
        it("입력받은 지뢰 개수만큼 지뢰 셀을 생성한다.") {
            val board = Board(Coordinate(BoardHeight(2), BoardWidth(2)), MineCount(3), FixedMineCellGenerator())
            val actual = board.create()
            actual.mineCells().size shouldBe 2
        }

        it("지뢰칸이 아닌 칸은 빈 칸이다.") {
            val board = Board(Coordinate(BoardHeight(2), BoardWidth(2)), MineCount(3), FixedMineCellGenerator())
            val actual = board.create()
            actual.emptyCells().size shouldBe 1
        }
    }
})
