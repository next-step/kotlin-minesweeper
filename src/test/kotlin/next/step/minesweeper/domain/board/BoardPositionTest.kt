package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BoardPositionTest : DescribeSpec({

    describe("BpardPosition") {
        val area = BoardArea.of(2, 2)
        context("near") {
            it("보드 위에 있는 위치의 주변 위치를 제공") {
                BoardPosition.of(0, 0, area).near() shouldBe BoardPositions(
                    setOf(
                        BoardPosition.of(-1, -1, area),
                        BoardPosition.of(-1, 0, area),
                        BoardPosition.of(-1, 1, area),
                        BoardPosition.of(0, -1, area),
                        BoardPosition.of(0, 1, area),
                        BoardPosition.of(1, -1, area),
                        BoardPosition.of(1, 0, area),
                        BoardPosition.of(1, 1, area),
                    ),
                )
            }
        }
    }
})
