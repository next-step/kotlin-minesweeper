package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardPositionTest : DescribeSpec({

    describe("BpardPosition") {
        val area = BoardArea.of(2, 2)
        context("near") {
            it("보드 위에 있는 위치의 주변 위치를 제공") {
                BoardPosition(0, 0).near() shouldBe BoardPositions(
                    setOf(
                        BoardPosition(-1, -1),
                        BoardPosition(-1, 0),
                        BoardPosition(-1, 1),
                        BoardPosition(0, -1),
                        BoardPosition(0, 1),
                        BoardPosition(1, -1),
                        BoardPosition(1, 0),
                        BoardPosition(1, 1),
                    ),
                )
            }
        }
    }
})
