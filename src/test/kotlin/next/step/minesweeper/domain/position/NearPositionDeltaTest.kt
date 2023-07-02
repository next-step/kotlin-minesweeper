package next.step.minesweeper.domain.position

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class NearPositionDeltaTest : DescribeSpec({

    describe("NearPositionDelta") {
        context("near") {
            it("주변 위치를 제공") {
                NearPositionDelta.near(1, 1) shouldBe listOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(2, 0),
                    Position(0, 1),
                    Position(2, 1),
                    Position(0, 2),
                    Position(1, 2),
                    Position(2, 2)
                )
            }
        }
    }

})
