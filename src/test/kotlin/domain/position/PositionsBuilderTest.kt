package domain.position

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.MineTotal
import minesweeper.domain.position.Position
import minesweeper.domain.position.RandomPositionPicker
import minesweeper.domain.position.positions

class PositionsBuilderTest : DescribeSpec({
    describe("위치 생성") {
        val positions = setOf(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(1, 1),
        )

        context("전체 positions와 전체 지뢰 개수(2개)이 주어지면") {
            val count = MineTotal(3)

            val result = positions(RandomPositionPicker()) {
                allPositions(positions)
                mineTotal(count)
            }

            it("전체 위치는 주어진 position와 같다") {
                result.value.size shouldBe 4
                result.value shouldBe positions
            }

            it("지뢰 개수는 입력 받은 지뢰 개수(3)와 같다") {
                result.minePositions.size shouldBe count.value
            }

            it("지뢰 위치는 주어진 전체 position 중에서 생성된다") {
                result.minePositions.forEach { it shouldBeIn positions }
            }
        }

        context("전체 positions가 주어지지 않으면") {
            it("위치 생성에 실패한다") {
                shouldThrowExactly<UninitializedPropertyAccessException> {
                    positions(RandomPositionPicker()) {
                        mineTotal(MineTotal(6))
                    }
                }
            }
        }

        context("지뢰 개수가 주어지지 않으면") {
            it("위치 생성에 실패한다") {
                shouldThrowExactly<IllegalStateException> {
                    positions(RandomPositionPicker()) {
                        allPositions(positions)
                    }
                }
            }
        }
    }
})
