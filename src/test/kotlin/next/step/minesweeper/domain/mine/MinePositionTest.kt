package next.step.minesweeper.domain.mine

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.throwable.shouldHaveMessage
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
    }
})
