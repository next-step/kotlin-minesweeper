package next.step.minesweeper.domain.board

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import next.step.minesweeper.domain.position.Position
import org.junit.jupiter.api.assertThrows

class BoardAreaTest : DescribeSpec({

    describe("BoardArea") {
        val area = BoardArea.of(3, 3)

        context("position이 area를 벗어나면 false") {
            withData(
                listOf(Position(-1, -1), Position(3, 3))
            ) { position ->
                (position in area) shouldBe false
            }
        }
        context("position이 area안에 있으면 true") {
            withData(
                listOf(Position(0, 0), Position(2, 2))
            ) { position ->
                (position in area) shouldBe true
            }
        }
        context("y가 height를 벗어나면 예외 발생") {
            withData(
                listOf(-1, 3)
            ) { y ->
                assertThrows<IllegalArgumentException> {
                    area.requireContains(1, y)
                }
            }
        }
        context("x가 width를 벗어나면 예외발생") {
            withData(
                listOf(-1, 3)
            ) { x ->
                assertThrows<IllegalArgumentException> {
                    area.requireContains(x, 1)
                }
            }
        }
        context("requireArea") {
            it("area에 특정 개수를 포함할 수 없으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    area.requireArea(10)
                }.shouldHaveMessage("9개보다 더 넣을 수 없습니다.")
            }
        }
        context("rangeMap") {
            area.rangeMap({ it }) { x, y -> x + y } shouldBe listOf(listOf(0, 1, 2), listOf(1, 2, 3), listOf(2, 3, 4))
        }
    }
})
