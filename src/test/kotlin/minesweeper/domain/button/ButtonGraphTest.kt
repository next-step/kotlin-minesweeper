package minesweeper.domain.button

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.button.vendor.ButtonVendor
import minesweeper.domain.position.Position
import minesweeper.domain.position.comma
import minesweeper.domain.position.data.PositionsDataSet
import minesweeper.view.toContentString
import org.junit.jupiter.api.assertThrows

class ButtonGraphTest : FunSpec({

    context("ButtonGraph 객체가 정상적으로 생성된다.") {
        val givenHeight = 10
        val givenWidth = 10
        val givenTotalButtonCount: Int = givenHeight * givenWidth

        val givenButtonVendor = ButtonVendor(givenHeight, givenWidth)

        withData(
            nameFn = { "height: $givenHeight, width: $givenWidth, mineCount: ${it.first}" },
            (0..givenTotalButtonCount).map {
                it to PositionsDataSet.testData(givenHeight, givenWidth, it)
            }
        ) { (mineCount, minePositions) ->
            val buttonGraph: ButtonGraph = givenButtonVendor.getButtonGraph(minePositions)

            buttonGraph shouldNotBe null
            buttonGraph.isNotEmpty() shouldBe true
            buttonGraph.mines shouldHaveSize mineCount
            buttonGraph.mines.all { it is Mine } shouldBe true
            buttonGraph.rowButtons() shouldHaveSize givenHeight

            Position.getAllPositionList(givenHeight, givenWidth).forEach {
                buttonGraph[it] shouldNotBe null
                buttonGraph[it.row, it.col] shouldBe buttonGraph[it]
            }
        }
    }

    context("Invalid Position에 해당하는 버튼을 조회시, IllegalArgumentException 발생") {
        val givenHeight = 10
        val givenWidth = 10
        val givenMineCount = 10

        val givenButtonVendor = ButtonVendor(givenHeight, givenWidth)

        withData(
            nameFn = { it.toContentString() },
            (givenHeight..givenHeight * 2) comma (givenWidth..givenWidth * 2)
        ) { invalidPosition ->
            val buttonGraph: ButtonGraph = givenButtonVendor.getButtonGraph(givenMineCount)

            val invalidRow = invalidPosition.row
            val invalidCol = invalidPosition.col

            assertThrows<IllegalArgumentException> {
                buttonGraph[invalidPosition]
            }

            assertThrows<IllegalArgumentException> {
                buttonGraph[invalidRow, invalidCol]
            }
        }
    }
})
