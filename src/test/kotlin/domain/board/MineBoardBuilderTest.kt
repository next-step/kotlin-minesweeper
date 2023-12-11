package domain.board

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.Height
import minesweeper.domain.board.MineCount
import minesweeper.domain.board.RandomPositionPicker
import minesweeper.domain.board.Width
import minesweeper.domain.board.mineBoard
import minesweeper.domain.cell.CellMark

class MineBoardBuilderTest : DescribeSpec({
    describe("보드 생성") {
        context("높이(6)와 너비(4), 지뢰 개수(3)가 주어지면") {
            val height = Height(6)
            val width = Width(4)
            val count = MineCount(3)

            val result = mineBoard(RandomPositionPicker()) {
                size(height, width)
                mineCount(count)
            }

            it("셀의 총 개수는 높이(6) 과 너비(4)의 곱(24)과 같다") {
                result.cells.size shouldBe 24
            }

            it("cell 은 행(0부터 5(높이(6)) * 열(0부터 3(너비(4)))에 대한 위치 값을 가진다") {
                val expect = (0..5).flatMap { row -> (0..3).map { col -> row to col } }.toSet()

                result.cells.forEach {
                    it.position.row to it.position.column shouldBeIn expect
                }
            }

            it("지뢰 개수는 입력 받은 지뢰 개수(3)와 같다") {
                result.cells.count { it.mark == CellMark.MINE } shouldBe count.value
            }
        }

        context("보드 사이즈가 주어지지 않으면") {
            it("보드 생성에 실패한다") {
                shouldThrowExactly<UninitializedPropertyAccessException> {
                    mineBoard(RandomPositionPicker()) {
                        mineCount(MineCount(6))
                    }
                }
            }
        }

        context("지뢰 개수가 주어지지 않으면") {
            it("보드 생성에 실패한다") {
                shouldThrowExactly<UninitializedPropertyAccessException> {
                    mineBoard(RandomPositionPicker()) {
                        size(Height(6), Width(4))
                    }
                }
            }
        }
    }
})
