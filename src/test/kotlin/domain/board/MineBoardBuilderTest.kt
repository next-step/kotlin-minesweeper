package domain.board

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.MineCount
import minesweeper.domain.RandomPositionPicker
import minesweeper.domain.cell.CellMark
import minesweeper.domain.cell.Position
import minesweeper.domain.mineBoard

class MineBoardBuilderTest : DescribeSpec({
    describe("보드 생성") {
        val positions = setOf(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(1, 1),
        )

        context("전체 positions와 지뢰 개수(2개)이 주어지면") {
            val count = MineCount(3)

            val result = mineBoard(RandomPositionPicker()) {
                allPositions(positions)
                mineCount(count)
            }

            it("셀의 위치는 주어진 positions와 같다") {
                result.cells.size shouldBe 4
                result.cells.map { it.position } shouldBe positions
            }

            it("지뢰 개수는 입력 받은 지뢰 개수(3)와 같다") {
                result.cells.count { it.mark == CellMark.MINE } shouldBe count.value
            }
        }

        context("전체 positions가 주어지지 않으면") {
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
                        allPositions(positions)
                    }
                }
            }
        }
    }
})
