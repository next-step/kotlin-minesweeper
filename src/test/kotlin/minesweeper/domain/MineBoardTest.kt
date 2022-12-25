package minesweeper.domain

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertDoesNotThrow

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

            mineBoard.toString().count { it == Mine.MINE_STRING.first() } shouldBe totalMineCount
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

            assertDoesNotThrow {
                println(mineBoard)
            }

            val mineBoardString = mineBoard.toString()
            mineBoardString.split("\n").size shouldBe height + 1
            mineBoardString.split("\n").any { it.length == width } shouldBe true
        }
    }
})
