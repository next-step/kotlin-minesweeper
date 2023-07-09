package minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import minesweeper.domain.test.TestObjectGenerator
import minesweeper.domain.vo.Position
import minesweeper.domain.vo.PositiveNumber

class CellBoardTest : BehaviorSpec({
    given("너비가 5인 경우") {
        val width = PositiveNumber(5)
        `when`("Mine 50개를 전달하면") {
            val mines = List(50) { Mine.ACTIVE }

            then("높이가 10인 보드가 생성된다.") {
                val cellBoard = CellBoard.generate(width, mines)
                cellBoard.board.size shouldBe 10
            }
        }

        `when`("Mine 49개를 전달하면") {
            val mines = List(49) { Mine.ACTIVE }

            then("예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    CellBoard.generate(width, mines)
                }
            }
        }
    }

    given("CellBoard가 주어진 경우") {
        val minePositions = listOf(
            Position.of(0, 0),
            Position.of(1, 1)
        )
        val cellBoard = TestObjectGenerator.cellBoard(minePositions, 3, 3)

        `when`("getNeighbors를 실행하면") {
            then("주변 위치를 구할 수 있다.") {
                listOf(
                    row(0, 0, 3),
                    row(1, 0, 5),
                    row(0, 1, 5),
                    row(1, 1, 8),
                    row(2, 0, 3),
                    row(0, 2, 3),
                    row(2, 1, 5),
                    row(1, 2, 5),
                    row(2, 2, 3),
                ).map { (x: Int, y: Int, neighborMineCount: Int) ->
                    cellBoard.getNeighbors(Position.of(x = x, y = y)).size shouldBe neighborMineCount

                }
            }
        }
    }

})

