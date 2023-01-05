package domain

import domain.strategy.CellGenerateStrategy
import domain.strategy.RandomGenerateStrategy
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

internal class GameTest : StringSpec({
    "가지고 있는 상태들로 지뢰찾기 보드를 만든다." {
        val row = Row(5)
        val column = Column(5)
        val mineCount = MineCount(5)
        val boardInfo = BoardInfo(row, column, mineCount)
        val strategy = RandomGenerateStrategy()

        val board = Game(boardInfo, strategy).createBoard()
        board.shouldBeInstanceOf<Board>()
    }

    /**
     * C * *
     * * * *
     * C C C
     */
    "입력된 좌표가 빈칸이고 아직 모든 빈칸이 개방되지 않았다면 게임의 상태는 진행중이다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(5))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1, 2, 3, 4, 5)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val inputCell = Blank(Coordinate(1 to 1))
        game.openBlankCell(board, inputCell)
        game.status shouldBe GameStatus.PROCEEDING
    }

    /**
     * C * *
     * * * *
     * C C C
     *
     * (3,1) 을 입력하면 (3,1), (3,2), (3,3) 3개 개방
     */
    "입력한 좌표 주변의 빈칸을 모두 개방한다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(5))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1, 2, 3, 4, 5)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val inputCell = Blank(Coordinate(3 to 1))
        game.openBlankCell(board, inputCell)

        val expectedSize = board.cells.count { it.status == Status.OPEN }
        expectedSize shouldBe 3
    }
})
