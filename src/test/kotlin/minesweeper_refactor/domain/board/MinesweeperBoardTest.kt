package minesweeper_refactor.domain.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper_refactor.domain.board.builder.minesweeperBoard

class MinesweeperBoardTest: StringSpec({

    "지뢰 보드판을 생성한 뒤 모든 보드판이 열리면 승리한다." {
        val minesweeperBoard = minesweeperBoard {
            mineCount(value = 0)
            boardSize {
                width(value = 10)
                height(value = 10)
            }
        }

        val matchState = minesweeperBoard.start()

        matchState shouldBe MatchState.WIN
    }

    "지뢰 보드판을 생성한 뒤 지뢰를 밟으면 패배한다." {
        val minesweeperBoard = minesweeperBoard {
            mineCount(value = 100)
            boardSize {
                width(value = 10)
                height(value = 10)
            }
        }

        val matchState = minesweeperBoard.start()

        matchState shouldBe MatchState.LOSE
    }
})
