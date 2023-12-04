package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeperBoardTest : StringSpec({
    val width = 10
    val height = 11
    val boardSize = MineSweeperBoardSize(width = width, height = height)

    val mineWidth = MineSweeperWidth.newInstance(width * height)

    "입력한 총 개수 (width * height) 만큼 생성한다." {
        val expected = 110
        mineWidth.size shouldBe expected
    }

    "MineSweeperBoard는 입력한 width, height 개수만큼 생성된다." {
        // height width 개수
        val mineSweeperBoard = MineSweeperBoard.newInstance(boardSize = boardSize, mineSweeperList = mineWidth)
        mineSweeperBoard.size shouldBe height
        mineSweeperBoard.forEach { mineSweeperBoardWidth ->
            mineSweeperBoardWidth.size shouldBe width
        }
    }

    "지뢰찾기 결과가 잘 출력된다." {
        val boardSize = MineSweeperBoardSize(width = 3, height = 3)
        val initialList =
            listOf("*", "0", "0", "0", "*", "0", "0", "0", "*")
        val expectedList =
            listOf("*", "2", "1", "2", "*", "2", "1", "2", "*")
        val board =
            MineSweeperBoard.newInstance(boardSize = boardSize, mineSweeperList = initialList.toMineSweeperWidth())
        val mineSweeper = MineSweeper(board)
        val resultMineSweeper = mineSweeper.doMineSweeper()

        resultMineSweeper.flatten() shouldBe expectedList
    }
})
