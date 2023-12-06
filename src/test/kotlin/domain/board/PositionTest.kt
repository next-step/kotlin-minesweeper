package domain.board

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Position

class PositionTest : DescribeSpec({

    describe("Position()") {
        context("0이상인 행과 0이상인 열로 위치를 생성") {
            val row = 0
            val column = 2

            val result = Position(row, column)

            it("생성 성공(생성된 위치는 주어진 위치)") {
                result.row shouldBe row
                result.column shouldBe column
            }
        }

        context("행이 0보다 작을 경우") {
            val row = -1
            it("위치 생성 실패") {
                shouldThrowExactly<IllegalArgumentException> {
                    Position(row, 2)
                }
            }
        }

        context("열이 0보다 작을 경우") {
            val column = -1

            it("위치 생성 실패") {
                shouldThrowExactly<IllegalArgumentException> {
                    Position(2, column)
                }
            }
        }
    }

    describe("adjacentPositions") {
        context("위치가 주어지면 (1, 1)") {
            val position = Position(1, 1)
            it("자신을 제외한 주변 8개 사각형에 포함된 지뢰 위치 반환") {
                val expect = setOf(
                    Position(0, 0),
                    Position(0, 1),
                    Position(0, 2),
                    Position(1, 0),
                    Position(1, 2),
                    Position(2, 0),
                    Position(2, 1),
                    Position(2, 2),
                )

                position.adjacentPositions shouldBe expect
            }
        }

        context("주변 8개의 위치가 위치의 범위 ( row > 0, column > 0) 을 넘는 위치가 주어지면 (0, 0)") {
            val position = Position(0, 0)
            it("위치의 범위 안에 들어오는 위치 반환") {
                val expect = setOf(
                    Position(0, 1),
                    Position(1, 0),
                    Position(1, 1),
                )

                position.adjacentPositions shouldBe expect
            }
        }
    }
})
