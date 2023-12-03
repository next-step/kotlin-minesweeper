package minesweeper.ui

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import minesweeper.domain.board.size.MineSweeperBoardSize

class MineBoardTest : StringSpec({
    "width 가 1보다 작을 경우 에러가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            MineSweeperBoardSize(width = 0, height = 1)
        }
    }

    "height 가 1보다 작을 경우 에러가 발생한다." {
        shouldThrow<IllegalArgumentException> {
            MineSweeperBoardSize(width = 1, height = 0)
        }
    }

    "width와 height가 1 이상이면 아무 에러 없이 객체를 생성한다." {
        MineSweeperBoardSize(width = 1, height = 1)
    }
})
