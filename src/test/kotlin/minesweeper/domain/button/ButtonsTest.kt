package minesweeper.domain.button

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.button.data.ButtonDataSet
import minesweeper.domain.position.Position
import minesweeper.domain.position.comma

class ButtonsTest : FunSpec({
    context("Buttons 객체가 정상적으로 생성된다.") {
        val givenHeight = 10
        val givenWidth = 10
        val givenMaxTotalCount = givenHeight * givenWidth

        val givenRowIndexRange = Position.indexRange(givenHeight)
        val givenColIndexRange = Position.indexRange(givenWidth)

        val givenAllPositions = (givenRowIndexRange comma givenColIndexRange).shuffled()

        withData(
            nameFn = { "pushableButtonCount: ${it.first}, mineCount: ${it.second}" },
            (0..givenMaxTotalCount).map { it to givenMaxTotalCount - it }
        ) { (pushableButtonCount, mineCount) ->
            val pushablePositions: List<Position> = givenAllPositions.take(pushableButtonCount)
            val minePositions: List<Position> = givenAllPositions.drop(pushableButtonCount).take(mineCount)

            pushablePositions shouldHaveSize pushableButtonCount
            minePositions shouldHaveSize mineCount

            val givenButtonList: List<Button> = ButtonDataSet.testDataList(pushablePositions, minePositions)

            val buttons = Buttons(givenButtonList)

            buttons shouldNotBe null
            buttons shouldContainAll givenButtonList
            buttons.size shouldBe pushableButtonCount + mineCount
        }
    }
})
