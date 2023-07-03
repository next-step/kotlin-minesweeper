package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertThrows

class BoardPositionTest : DescribeSpec({

    describe("BpardPosition") {
        val area = BoardArea.of(2, 2)
        context("생성") {
            it("board 높이를 벗어나게 위치를 생성하면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    BoardPosition.of(0, 2, area)
                }.shouldHaveMessage("y 위치는 0보다 크고, 2 보다 작아야 합니다.")
            }
            it("board 너비를 벗어나게 지뢰를 심으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    BoardPosition.of(2, 0, area)
                }.shouldHaveMessage("x 위치는 0보다 크고, 2 보다 작아야 합니다.")
            }
        }
        context("near") {
            it("보드 위에 있는 위치의 주변 위치를 제공") {
                BoardPosition.of(0, 0, area).near() shouldBe BoardPositions(
                    setOf(
                        BoardPosition.of(1, 1, area),
                        BoardPosition.of(0, 1, area),
                        BoardPosition.of(1, 0, area),
                    ),
                )
            }
        }
    }
})
