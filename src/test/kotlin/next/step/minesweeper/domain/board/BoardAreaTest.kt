package next.step.minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
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
                listOf(BoardPosition(-1, -1), BoardPosition(3, 3)),
            ) { position ->
                (position in area) shouldBe false
            }
        }
        context("position이 area안에 있으면 true") {
            withData(
                listOf(BoardPosition(0, 0), BoardPosition(2, 2)),
            ) { position ->
                (position in area) shouldBe true
            }
        }
        context("y가 height를 벗어나면 예외 발생") {
            withData(
                listOf(-1, 3),
            ) { y ->
                assertThrows<IllegalArgumentException> {
                    area.requireContains(1, y)
                }
            }
        }
        context("x가 width를 벗어나면 예외발생") {
            withData(
                listOf(-1, 3),
            ) { x ->
                assertThrows<IllegalArgumentException> {
                    area.requireContains(x, 1)
                }
            }
        }
        context("requireArea") {
            it("area에 특정 개수를 포함할 수 없으면 예외 발생") {
                assertThrows<IllegalArgumentException> {
                    area.checkMaxCount(10)
                }.shouldHaveMessage("9개보다 더 많을 수 없습니다.")
            }
        }
        context("rangeMap") {
            it("area에 포함되는 height range, width range에 대한 mapping 수행") {
                area.rangeMap({ it }) { x, y -> x + y } shouldBe listOf(
                    listOf(0, 1, 2),
                    listOf(1, 2, 3),
                    listOf(2, 3, 4),
                )
            }
        }
        context("select") {
            it("선택된 위치가 area를 벗어나면 예외발생") {
                shouldThrow<IllegalArgumentException> {
                    area.select { Position(-1, 0) }
                }
            }
            it("선택된 위치가 area를 벗어나지 않으면 BoardPosition으로 리턴") {
                area.select { Position(0, 0) } shouldBe BoardPosition(0, 0)
            }
        }

        context("near") {
            it("보드 위에 있는 위치의 주변 위치를 제공") {
                area.near(BoardPosition(0, 0)) shouldBe BoardPositions(
                    setOf(
                        BoardPosition(1, 1),
                        BoardPosition(0, 1),
                        BoardPosition(1, 0),
                    ),
                )
            }
        }
    }
})
