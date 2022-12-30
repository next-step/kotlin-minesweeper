package minesweeper.domain

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertThrows

class MineSweeperGameTest : FunSpec({

    context("높이와 너비, 지뢰 개수를 통해 MineSweeperGame가 정상적으로 생성된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(10, 10, 100),
                Tuple3(10, 10, 0),
                Tuple3(25, 10, 50),
                Tuple3(20, 30, 400),
            )
        ) { (height, width, mineCount) ->
            val mineSweeperGame = MineSweeperGame(
                height = height,
                width = width,
                mineCount = mineCount
            )

            mineSweeperGame shouldNotBe null
            mineSweeperGame.buttonGraph shouldNotBe null
        }
    }

    context("ButtonGraph를 통해 MineSweeperGame가 정상적으로 생성된다.") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 10),
                Tuple3(10, 10, 100),
                Tuple3(10, 10, 0),
                Tuple3(25, 10, 50),
                Tuple3(20, 30, 400),
            )
        ) { (height, width, mineCount) ->
            val mineSweeperGame = MineSweeperGame(height, width, mineCount)

            mineSweeperGame shouldNotBe null
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
        ) { (height, width, mineCount) ->
            assertThrows<IllegalArgumentException> {
                MineSweeperGame(height, width, mineCount)
            }
        }
    }

    context("지뢰 개수가 초과할 경우, IllegalArgumentException 발생") {
        withData(
            nameFn = { "$it" },
            listOf(
                Tuple3(10, 10, 102),
                Tuple3(5, 5, 27),
                Tuple3(10, 10, 125)
            )
        ) { (height, width, tooMuchMineCount) ->
            assertThrows<IllegalArgumentException> {
                MineSweeperGame(height, width, tooMuchMineCount)
            }
        }
    }
})
