package minesweeper.ui

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import minesweeper.MineSweeper
import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeperTest : StringSpec({
    "mine count 가 1보다 작을 경우 에러를 발생시킨다." {
        val mineBoard = MineSweeperBoardSize(1, 1)
        shouldThrow<IllegalArgumentException> {
            MineSweeper(mineBoard, 0)
        }
    }

    "mine count가 1 이상인 경우 에러를 발생시키지 않는다" {
        val mineBoard = MineSweeperBoardSize(1, 1)
        MineSweeper(mineBoard, 1)
    }
})
