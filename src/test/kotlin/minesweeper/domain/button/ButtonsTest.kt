package minesweeper.domain.button

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.button.data.ButtonDataSet

class ButtonsTest : FunSpec({
    context("Buttons 객체가 정상적으로 생성된다.") {
        val givenTotalCount = 100
        withData(
            nameFn = { "pushableButtonCount: ${it.first}, mineCount: ${it.second}" },
            (0..givenTotalCount).map { it to givenTotalCount - it }
        ) { (pushableButtonCount, mineCount) ->
            val givenButtonList = ButtonDataSet.testData(pushableButtonCount, mineCount)
            val buttons = Buttons(givenButtonList)

            buttons shouldNotBe null
            buttons shouldContainAll givenButtonList
            buttons.size shouldBe pushableButtonCount + mineCount
        }
    }
})
