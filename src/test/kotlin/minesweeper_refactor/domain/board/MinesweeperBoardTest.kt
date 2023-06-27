package minesweeper_refactor.domain.board

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper_refactor.domain.block.Block
import minesweeper_refactor.domain.block.BlockState
import minesweeper_refactor.domain.block.Blocks
import minesweeper_refactor.domain.board.builder.minesweeperBoard
import minesweeper_refactor.domain.coordinate.Coordinate
import minesweeper_refactor.domain.game.GameEvent
import minesweeper_refactor.domain.game.MinesweeperGame

class MinesweeperBoardTest : StringSpec({

    "지뢰 보드판을 생성한 뒤 모든 보드판이 열리면 승리한다." {
        val minesweeperBoard = minesweeperBoard {
            mineCount(value = 0)
            boardSize {
                width(value = 10)
                height(value = 10)
            }
        }

        val minesweeperGame = MinesweeperGame(minesweeperBoard = minesweeperBoard)

        val matchState = minesweeperGame.start(
            gameEvent = GameEvent(
                openCoordinateEvent = { Coordinate(x = 1, y = 1) },
                currentBoardEvent = { },
            ),
        )

        matchState shouldBe MatchState.WIN
    }

    "지뢰 보드판을 생성한 뒤 지뢰를 밟으면 패배한다." {
        val mineBlocks = Blocks(
            blocks = listOf(
                Block(
                    coordinate = Coordinate(x = 0, y = 0),
                    blockState = BlockState.MINE,
                ),
            ),
        )

        val numberBlocks = Blocks(
            blocks = listOf(
                Block.of(
                    coordinate = Coordinate(x = 1, y = 0),
                    aroundMineCount = 1,
                ),
                Block.of(
                    coordinate = Coordinate(x = 1, y = 1),
                    aroundMineCount = 1,
                ),
                Block.of(
                    coordinate = Coordinate(x = 0, y = 1),
                    aroundMineCount = 1,
                ),
            ),
        )

        /*
            X 1
            1 1
         */
        val minesweeperBoard = MinesweeperBoard(numberBlocks = numberBlocks, mineBlocks = mineBlocks)

        val minesweeperGame = MinesweeperGame(minesweeperBoard = minesweeperBoard)

        val matchState = minesweeperGame.start(
            gameEvent = GameEvent(
                openCoordinateEvent = { Coordinate(x = 0, y = 0) },
                currentBoardEvent = { },
            ),
        )

        matchState shouldBe MatchState.LOSE
    }
})
