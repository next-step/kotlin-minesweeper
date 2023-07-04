package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.domain.position.Positions

class BoardPositionTest : DescribeSpec({

    describe("BpardPosition") {
        val area = BoardArea.of(2, 2)
        context("near") {
            it("보드 위에 있는 위치의 주변 위치를 제공") {
                Position(0, 0).near() shouldBe Positions(
                    setOf(
                        Position(-1, -1),
                        Position(-1, 0),
                        Position(-1, 1),
                        Position(0, -1),
                        Position(0, 1),
                        Position(1, -1),
                        Position(1, 0),
                        Position(1, 1),
                    ),
                )
            }
        }
    }
})
