package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BoardTest {
    @ParameterizedTest
    @CsvSource(
        "1, 1, 1",
        "2, 2, 2",
    )
    fun `지뢰 배치를 테스트`(
        width: Int,
        height: Int,
        mineCount: Int,
    ) {
        val board = Board(Width(width), Height(height))
        board.placeMines(MineCount(mineCount))

        val count: Int =
            board.sumOf { column ->
                column.count { cell ->
                    cell.symbol == CellState.MINE.getSymbol()
                }
            }

        mineCount shouldBe count
    }
}
