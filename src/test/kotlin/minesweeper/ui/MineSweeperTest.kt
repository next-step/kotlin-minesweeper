package minesweeper.ui

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import minesweeper.MineSweeper
import minesweeper.domain.board.size.MineSweeperBoardSize

class MineSweeperTest : StringSpec({
    "mine count 가 1보다 작을 경우" {
        val mineBoard = MineSweeperBoardSize(1, 1)
        shouldThrow<IllegalArgumentException> {
            MineSweeper(mineBoard, 0)
        }
    }
})
