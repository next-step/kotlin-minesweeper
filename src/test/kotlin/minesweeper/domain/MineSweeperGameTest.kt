package minesweeper.domain

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.position.vendor.PositionsVendor
import minesweeper.util.indexRange
import minesweeper.view.MineBoardView
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class MineSweeperGameTest : FunSpec({
    val givenPositionsVendor = PositionsVendor()

    context("높이와 너비, 지뢰 개수를 통해 MineSweeperGame가 정상적으로 생성된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(5, 5, 25),
                Tuple3(10, 10, 20)
            )
        ) { (height, width, totalMineCount) ->
            val givenMinePositions = givenPositionsVendor.randomPositions(
                rowIndexRange = height.indexRange(),
                colIndexRange = width.indexRange(),
                totalCount = totalMineCount
            )
            val mineSweeperGame = MineSweeperGame.of(
                height = height,
                width = width,
                totalMineCount = totalMineCount,
                minePositions = givenMinePositions
            )
            mineSweeperGame shouldNotBe null
            mineSweeperGame.height shouldBe height
            mineSweeperGame.width shouldBe width
            mineSweeperGame.totalMineCount shouldBe totalMineCount
            mineSweeperGame.buttonGraph shouldNotBe null
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
                totalCount = totalMineCount
            )

            assertThrows<IllegalArgumentException> {
                MineSweeperGame.of(height, width, totalMineCount, givenMinePositions)
            }
        }
    }

    context("지뢰 개수는 0미만 일때, IllegalArgumentException 발생") {
        val givenTotalMineCount = 10

        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, -1),
                Tuple3(5, 5, -2),
                Tuple3(15, 10, -5),
                Tuple3(25, 25, -10),
                Tuple3(100, 100, -101),
            )
        ) { (height, width, totalMineCount) ->
            val givenMinePositions = givenPositionsVendor.randomPositions(
                rowIndexRange = height.indexRange(),
                colIndexRange = width.indexRange(),
                totalCount = givenTotalMineCount
            )

            assertThrows<IllegalArgumentException> {
                MineSweeperGame.of(height, width, totalMineCount, givenMinePositions)
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
            val givenWrongTotalMineCount = (height * width).indexRange().first { it != totalMineCount }
            val givenWrongMinePositions = givenPositionsVendor.randomPositions(
                rowIndexRange = height.indexRange(),
                colIndexRange = width.indexRange(),
                totalCount = givenWrongTotalMineCount
            )

            assertThrows<IllegalArgumentException> {
                MineSweeperGame.of(height, width, totalMineCount, givenWrongMinePositions)
            }
        }
    }

    context("MineSweeperGame이 정상적으로 출력된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(5, 5, 25),
                Tuple3(10, 10, 20)
            )
        ) { (height, width, totalMineCount) ->
            val givenMinePositions = givenPositionsVendor.randomPositions(
                rowIndexRange = height.indexRange(),
                colIndexRange = width.indexRange(),
                totalCount = totalMineCount
            )

            val mineSweeperGame = MineSweeperGame.of(
                height = height,
                width = width,
                totalMineCount = totalMineCount,
                minePositions = givenMinePositions
            )

            val mineBoardView = MineBoardView()

            assertDoesNotThrow {
                mineBoardView.printlnMineBoard(mineSweeperGame)
            }
        }
    }
})
