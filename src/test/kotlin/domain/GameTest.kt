package domain

import domain.strategy.RandomGenerateStrategy
import io.kotest.core.spec.style.StringSpec
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
})
