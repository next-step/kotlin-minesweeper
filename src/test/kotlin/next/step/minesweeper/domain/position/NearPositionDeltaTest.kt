package next.step.minesweeper.domain.position

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.minesweeper.domain.board.BoardArea

class NearPositionDeltaTest : DescribeSpec({

    describe("NearPositionDelta") {
        context("nearInBoard") {
            it("주변 위치를 제공") {
                NearPositionDelta.nearInArea(1, 1, BoardArea.of(3, 3)) shouldBe listOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(2, 0),
                    Position(0, 1),
                    Position(2, 1),
                    Position(0, 2),
                    Position(1, 2),
                    Position(2, 2),
                )
            }
            it("BoardArea를 벗어난 위치는 빼고 주변 위치를 제공") {
                NearPositionDelta.nearInArea(0, 0, BoardArea.of(3, 3)) shouldBe listOf(
                    Position(1, 0),
                    Position(0, 1),
                    Position(1, 1),
                )
            }
        }
    }
})
