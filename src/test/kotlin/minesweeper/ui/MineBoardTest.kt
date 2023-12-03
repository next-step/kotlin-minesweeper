package minesweeper.ui

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import minesweeper.domain.board.size.MineSweeperBoard

class MineBoardTest : StringSpec({
    "width 가 1보다 작을 경우" {
        shouldThrow<IllegalArgumentException> {
            MineSweeperBoard(width = 0, height = 1)
        }
    }

    "height 가 1보다 작을 경우" {
        shouldThrow<IllegalArgumentException> {
            MineSweeperBoard(width = 1, height = 0)
        }
    }
})
