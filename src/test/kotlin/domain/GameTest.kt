package domain

import domain.strategy.CellGenerateStrategy
import domain.strategy.RandomGenerateStrategy
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.lang.IllegalArgumentException

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

    "입력된 좌표가 없는 좌표라면 에러가 발생한다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(9))
        val game = Game(boardInfo, RandomGenerateStrategy())
        val board = game.createBoard()

        val inputCoordinate = Coordinate(6 to 6)
        shouldThrow<IllegalArgumentException> {
            game.openCell(board, inputCoordinate)
        }
    }

    "입력된 좌표가 지뢰의 좌표라면 게임에서 패배한다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(1))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val inputCoordinate = Coordinate(1 to 2)

        game.openCell(board, inputCoordinate)
        game.status shouldBe GameStatus.LOSE
    }

    "모든 빈칸의 좌표가 열려있다면(지뢰 찾기에 성공했다면) 게임에서 승리한다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(1))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        board.cells
            .filterIsInstance<Blank>()
            .forEach { it.open() }

        game.openCell(board, Coordinate(2 to 2))
        game.status shouldBe GameStatus.WIN
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

        game.openCell(board, Coordinate(1 to 1))
        game.status shouldBe GameStatus.PROCEEDING
    }
})
