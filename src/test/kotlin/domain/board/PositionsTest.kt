package domain.board

import Positions
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Position

class PositionsTest : DescribeSpec({
    describe("isMine") {
        val positions = Positions(
            row = 3,
            column = 3,
            minePositions = setOf(Position(1, 1))
        )
        context("지뢰 위치에 포함되어 있으면") {
            val result = positions.isMine(Position(1, 1))

            it("isMine = true") {
                result shouldBe true
            }
        }

        context("지뢰 위치에 포함되어 있지 않으면") {
            val result = positions.isMine(Position(0, 0))

            it("isMine = false") {
                result shouldBe false
            }
        }

        context("위치 범위에 포함되어 있지 않으면") {
            val result = positions.isMine(Position(100, 100))

            it("isMine = false") {
                result shouldBe false
            }
        }
    }

    describe("adjacentMineCountByPosition") {
        context("위치가 주어지면 (지뢰 1개)") {
            val positions = Positions(
                row = 4,
                column = 4,
                minePositions = setOf(Position(1, 1))
            )

            it("인접한 지뢰의 수를 반환") {
                val result = positions.adjacentMineCountByPosition

                result shouldBe mapOf(
                    Position(0, 0) to 1,
                    Position(0, 1) to 1,
                    Position(0, 2) to 1,

                    Position(1, 0) to 1,
                    Position(1, 2) to 1,
                    Position(2, 0) to 1,
                    Position(2, 1) to 1,
                    Position(2, 2) to 1,

                    Position(0, 3) to 0,
                    Position(1, 1) to 0,
                    Position(1, 3) to 0,
                    Position(2, 3) to 0,
                    Position(3, 0) to 0,
                    Position(3, 1) to 0,
                    Position(3, 2) to 0,
                    Position(3, 3) to 0,
                )
            }
        }

        context("위치가 주어지면 (지뢰 2개)") {
            val positions = Positions(
                row = 4,
                column = 4,
                minePositions = setOf(Position(0, 0), Position(0, 1)),
            )

            it("인접한 지뢰의 수를 반환") {
                val result = positions.adjacentMineCountByPosition

                result shouldBe mapOf(
                    Position(0, 0) to 1,
                    Position(0, 1) to 1,
                    Position(0, 2) to 1,
                    Position(1, 2) to 1,

                    Position(1, 0) to 2,
                    Position(1, 1) to 2,

                    Position(0, 3) to 0,
                    Position(1, 3) to 0,
                    Position(2, 0) to 0,
                    Position(2, 1) to 0,
                    Position(2, 2) to 0,
                    Position(2, 3) to 0,
                    Position(3, 0) to 0,
                    Position(3, 1) to 0,
                    Position(3, 2) to 0,
                    Position(3, 3) to 0,
                )
            }
        }
    }
})
