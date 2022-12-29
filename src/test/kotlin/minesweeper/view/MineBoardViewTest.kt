package minesweeper.view

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import minesweeper.domain.MineSweeperGame
import org.junit.jupiter.api.assertDoesNotThrow

class MineBoardViewTest : FunSpec({
    context("MineSweeperGame이 정상적으로 출력된다.") {

        withData(
            nameFn = { "height: ${it.a}, width: ${it.b}, mineCount: ${it.c}" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(5, 5, 25),
                Tuple3(10, 10, 20)
            )
        ) { (height, width, mineCount) ->
            val mineSweeperGame = MineSweeperGame(
                height = height,
                width = width,
                mineCount = mineCount
            )

            val mineBoardView = MineBoardView()

            assertDoesNotThrow {
                mineBoardView.printlnMineBoard(mineSweeperGame)
            }
        }
    }
})
