package minesweeper.domain.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardSize
import minesweeper.domain.Coordinate
import minesweeper.domain.MinesweeperBoardGenerator
import minesweeper.domain.PositiveNumber
import minesweeper.domain.flag.MatchState

class MinesweeperBoardTest : StringSpec({

    "지뢰 보드판을 생성한 뒤 모든 보드판이 열리면 승리한다." {
        val board = MinesweeperBoardGenerator.generate(
            boardSize = BoardSize(width = 2, height = 2),
            mineCount = PositiveNumber(value = 0),
        )

        val matchState = board.start(
            openCoordinateEvent = { Coordinate(x = 1, y = 1) },
            currentBoardEvent = { },
        )

        matchState shouldBe MatchState.WIN
    }

    "지뢰 보드판을 생성한 뒤 지뢰를 밟으면 패배한다." {
        val board = MinesweeperBoardGenerator.generate(
            boardSize = BoardSize(width = 10, height = 10),
            mineCount = PositiveNumber(value = 4),
        )

        val mineBlock = board.sortedBlocks()
            .find { it.hasMine }!!

        val matchState = board.start(
            openCoordinateEvent = { mineBlock.coordinate },
            currentBoardEvent = { },
        )

        matchState shouldBe MatchState.LOSE
    }
})
