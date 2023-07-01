package next.step.minesweeper.domain.position

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertThrows

class PointTest : DescribeSpec({

    describe("Point") {
        context("생성") {
            it("위치 y가 0보다 작으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    Position(0, -1)
                }.shouldHaveMessage("위치 y는 0 보다 작을 수 없습니다.")
            }

            it("위치 x가 0보다 작으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    Position(-1, 0)
                }.shouldHaveMessage("위치 x는 0 보다 작을 수 없습니다.")
            }
            it("base는 기준이 되는 포인트 제공") {
                Position.base() shouldBe Position(0, 0)
            }
        }
    }
})
