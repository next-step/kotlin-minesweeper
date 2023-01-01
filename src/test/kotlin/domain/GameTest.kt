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
            game.getBlankCell(inputCoordinate, board)
        }
    }

    "입력된 좌표가 지뢰의 좌표라면 에러가 발생한다." {
        val boardInfo = BoardInfo(Row(5), Column(5), MineCount(1))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val inputCoordinate = Coordinate(1 to 2)
        shouldThrow<IllegalArgumentException> {
            game.getBlankCell(inputCoordinate, board)
        }
    }

    /**
     * C * C
     * C C C
     * C C C
     */
    "입력된 좌표가 존재하고 빈칸의 좌표라면 조회된 Blank Cell 을 반환한다." {
        val boardInfo = BoardInfo(Row(3), Column(3), MineCount(1))
        val customGenerateStrategy = CellGenerateStrategy { _, _ -> Locations(listOf(1)) }
        val game = Game(boardInfo, customGenerateStrategy)
        val board = game.createBoard()

        val inputCoordinate = Coordinate(2 to 1)
        val result = game.getBlankCell(inputCoordinate, board)

        result shouldBe Blank(coordinate = Coordinate(2 to 1), minesAroundCount = 1)
    }
})
