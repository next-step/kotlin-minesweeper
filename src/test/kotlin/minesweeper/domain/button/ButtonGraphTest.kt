package minesweeper.domain.button

import io.kotest.core.Tuple3
import io.kotest.core.Tuple4
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.position.data.PositionsDataSet
import minesweeper.domain.position.vendor.PositionsVendor
import minesweeper.util.indexRange
import org.junit.jupiter.api.assertThrows

class ButtonGraphTest : FunSpec({
    val givenPositionsVendor = PositionsVendor()

    context("ButtonGraph 객체가 정상적으로 생성된다.") {
        val givenHeight = 10
        val givenWidth = 10
        val givenTotalCount = givenHeight * givenWidth

        val givenRowIndexRange = (0 until givenHeight)
        val givenColIndexRange = (0 until givenWidth)

        withData(
            nameFn = { "height: ${it.a}, width: ${it.b}, totalCount: ${it.c}, mineCount: ${it.d.size}" },
            (0..givenTotalCount).map {
                Tuple4(
                    givenHeight,
                    givenWidth,
                    it,
                    PositionsDataSet.testData(it, givenRowIndexRange, givenColIndexRange)
                )
            }
        ) { (height, width, totalMineCount, minePositions) ->
            val buttonGraph = ButtonGraph.of(height, width, totalMineCount, minePositions)

            buttonGraph shouldNotBe null
            buttonGraph.isNotEmpty() shouldBe true
            buttonGraph.rowIndexRange shouldBe givenRowIndexRange
            buttonGraph.colIndexRange shouldBe givenColIndexRange
            buttonGraph.getAllPositions() shouldHaveSize givenTotalCount
            buttonGraph.getAllPositions().forEach { position ->
                buttonGraph[position] shouldNotBe null
                buttonGraph[position.row, position.col] shouldNotBe null
                buttonGraph[position] shouldBe buttonGraph[position.row, position.col]
            }
        }
    }

    context("높이와 너비가 0이하 일때, IllegalArgumentException 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(-10, 10, 10),
                Tuple3(10, -10, 10),
                Tuple3(10, 0, 10),
                Tuple3(0, 10, 10),
            )
        ) { (height, width, totalMineCount) ->
            val givenMinePositions = givenPositionsVendor.randomPositions(
                rowIndexRange = 10.indexRange(),
                colIndexRange = 10.indexRange(),
                totalMineCount
            )

            assertThrows<IllegalArgumentException> {
                ButtonGraph.of(height, width, totalMineCount, givenMinePositions)
            }
        }
    }

    context("지뢰 개수는 0미만 일때, IllegalArgumentException 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, -1),
                Tuple3(5, 5, -2),
                Tuple3(15, 10, -5),
                Tuple3(25, 25, -10),
                Tuple3(10, 10, -101),
            )
        ) { (height, width, invalidTotalMineCount) ->
            val givenMinePositions = givenPositionsVendor.randomPositions(
                rowIndexRange = height.indexRange(),
                colIndexRange = width.indexRange(),
                totalCount = (0..height * width).random()
            )

            assertThrows<IllegalArgumentException> {
                ButtonGraph.of(height, width, invalidTotalMineCount, givenMinePositions)
            }
        }
    }

    context("지뢰 개수 검증 실패시, IllegalArgumentException 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(5, 5, 25),
                Tuple3(10, 10, 20)
            )
        ) { (height, width, totalMineCount) ->
            val invalidTotalMineCount = (height * width).indexRange().first { it != totalMineCount }
            val invalidMinePositions = givenPositionsVendor.randomPositions(
                rowIndexRange = height.indexRange(),
                colIndexRange = width.indexRange(),
                totalCount = invalidTotalMineCount
            )

            assertThrows<IllegalArgumentException> {
                ButtonGraph.of(height, width, totalMineCount, invalidMinePositions)
            }
        }
    }
})
