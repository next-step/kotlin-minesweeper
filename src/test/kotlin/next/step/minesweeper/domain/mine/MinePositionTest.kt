package next.step.minesweeper.domain.mine

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.position.Position
import org.junit.jupiter.api.assertThrows

class MinePositionTest : DescribeSpec({

    describe("Position") {
        context("생성") {
            it("위치 y가 0보다 작으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MinePosition(0, -1)
                }.shouldHaveMessage("위치 y는 0 보다 작을 수 없습니다.")
            }
            it("위치 x가 0보다 작으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    MinePosition(-1, 0)
                }.shouldHaveMessage("위치 x는 0 보다 작을 수 없습니다.")
            }
        }
        context("nearMinePoints") {
            it("지뢰 주변 위치를 제공") {
                MinePosition(1, 1).nearMinePositions() shouldBe listOf(
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
