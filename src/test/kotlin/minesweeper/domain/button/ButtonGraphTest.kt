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
import minesweeper.domain.position.toPositions
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

    context("특정 Button 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수를 정상적으로 리턴한다.") {
        val givenHeight = 10
        val givenWidth = 10

        val givenButtonVendor = ButtonVendor(givenHeight, givenWidth)
        val givenMinePositions = listOf(
            1 comma 1,
            1 comma 2,
            1 comma 3,
            2 comma 1,
            2 comma 3,
            3 comma 1,
            3 comma 2,
            3 comma 3
        ).toPositions()

        val givenButtonGraph = givenButtonVendor.getButtonGraph(givenMinePositions)

        withData(
            nameFn = { "${it.first.toContentString()} -> expectedMineCount: ${it.second}" },
            listOf(
                0 comma 0 to 1,
                0 comma 1 to 2,
                0 comma 2 to 3,
                0 comma 3 to 2,
                0 comma 4 to 1,
                0 comma 5 to 0,
                1 comma 0 to 2,
                1 comma 4 to 2,
                1 comma 5 to 0,
                2 comma 0 to 3,
                2 comma 2 to 8,
                2 comma 4 to 3,
                2 comma 5 to 0,
                3 comma 0 to 2,
                3 comma 4 to 2,
                3 comma 5 to 0,
                4 comma 0 to 1,
                4 comma 1 to 2,
                4 comma 2 to 3,
                4 comma 3 to 2,
                4 comma 4 to 1,
                4 comma 5 to 0
            )
        ) { (position, expectedMineCount) ->
            givenButtonGraph.getMineCountAroundOf(position) shouldBe expectedMineCount
        }
    }
})
