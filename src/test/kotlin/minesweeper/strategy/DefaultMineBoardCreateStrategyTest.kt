package minesweeper.strategy

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.SymbolType
import minesweeper.request.MinesCreateRequest

class DefaultMineBoardCreateStrategyTest : BehaviorSpec({
    val mineBoardCreateStrategy = DefaultMineBoardCreateStrategy

    Given("유효한 높이와 너비정보가 있다.") {
        val width = 10
        val height = 10

        When("지뢰 갯수가 유효할 때") {
            val mineCapacity = (1..width * height).random()

            Then("지뢰 정보 일급 컬렉션을 반환한다.") {
                val actual = mineBoardCreateStrategy.create(
                    request = MinesCreateRequest(
                        height = height,
                        width = width,
                        mineCapacity = mineCapacity
                    )
                )

                actual.area shouldBe Area(width, height)

                actual.rows.forEach {
                    it.realSize shouldBe width
                }

                actual.rows.sumOf { line ->
                    line.count { it.hasSymbolType(SymbolType.MINE) }
                } shouldBe mineCapacity
            }
        }

        When("지뢰 갯수가 유효하지 않을 때 ") {
            val mineCapacity = (width * height) + 1

            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    mineBoardCreateStrategy.create(
                        request = MinesCreateRequest(
                            height = height,
                            width = width,
                            mineCapacity = mineCapacity
                        )
                    )
                }
            }
        }
    }
})
