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

    "모든 빈칸을 open 했다면 지뢰찾기 게임에서 승리한다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(9))
        val game = Game(boardInfo, RandomGenerateStrategy())
        val board = game.createBoard()
        board.cells.filterIsInstance<Blank>().forEach { it.open() }

        val result = game.play(
            board,
            { Coordinate(1 to 1) },
            { print(it) }
        )

        result shouldBe ResultStatus.WIN
    }

    "지뢰를 밟은 경우 지뢰찾기 게임에서 패배한다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(1))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val result = game.play(
            board,
            { Coordinate(1 to 2) },
            { print(it) }
        )

        result shouldBe ResultStatus.LOSE
    }
})
