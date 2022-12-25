package minesweeper.domain

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import minesweeper.domain.button.Mine
import minesweeper.view.MineBoardView
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class MineBoardTest : FunSpec({

    context("높이와 너비, 지뢰 개수를 통해 MineBoard가 정상적으로 생성된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(5, 5, 25),
                Tuple3(10, 10, 20)
            )
        ) { (height, width, totalMineCount) ->
            val mineBoard = MineBoard(height, width, totalMineCount)
            mineBoard shouldNotBe null
            mineBoard.height shouldBe height
            mineBoard.width shouldBe width
            mineBoard.totalMineCount shouldBe totalMineCount
            mineBoard.buttons shouldNotBe null
            mineBoard.buttons.flatMap { it }.count { it is Mine } shouldBe totalMineCount
        }
    }

    context("높이와 너비, 지뢰 개수 검증을 통해 IllegalArgumentException이 발생한다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(-10, 10, 10),
                Tuple3(10, -10, 10),
                Tuple3(10, 10, -10),
                Tuple3(10, 10, 101),

            )
        ) { (height, width, totalMineCount) ->
            assertThrows<IllegalArgumentException> {
                MineBoard(height, width, totalMineCount)
            }
        }
    }

    context("높이와 너비, 지뢰 개수를 통해 MineBoard가 정상적으로 출력된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(5, 5, 25),
                Tuple3(10, 10, 20)
            )
        ) { (height, width, totalMineCount) ->
            val mineBoard = MineBoard(height, width, totalMineCount)
            val mineBoardView = MineBoardView()

            assertDoesNotThrow {
                mineBoardView.printlnMineBoard(mineBoard)
            }
        }
    }
})
