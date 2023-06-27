package minesweeper.domain.dsl

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.SymbolType
import minesweeper.strategy.DefaultMineBoardCreateStrategy

class MineBoardBuilderTest : BehaviorSpec({

    Given("유효한 너비,높이,지뢰 갯수, 생성 전략이 있다.") {
        val width = 10
        val height = 8
        val mineCapacity = 20
        val strategy = DefaultMineBoardCreateStrategy

        When("모든 유효한 값으로 지뢰찾기 생성 DSL을 호출할 경우 ") {
            val actual = buildMineBoard {
                width(width)
                height(height)
                mineCapacity(mineCapacity)
                strategy(strategy)
            }

            Then("정상적으로 지뢰찾기 보드가 생성된다.") {
                actual.area shouldBe Area(width, height)

                actual.lines shouldHaveSize height
                actual.lines.forEach {
                    it shouldHaveSize width
                }
                actual.lines.sumOf {
                    it.count { point -> point.getSymbol() == SymbolType.MINE }
                } shouldBe mineCapacity
            }
        }
    }
})
