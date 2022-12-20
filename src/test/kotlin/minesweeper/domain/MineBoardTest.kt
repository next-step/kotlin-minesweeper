package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineBoardTest {

    @DisplayName("보드판에 지뢰를 확인합니다")
    @Test
    fun checkMine() {
        val rowCount = RowCount(10)
        val columnCount = ColumnCount(10)
        val mineCount = MineCount(
            count = 3,
            boardSize = rowCount * columnCount,
            minePositionList = listOf(1, 2, 3)
        )

        val board = MineBoard(rowCount, columnCount, mineCount)

        board.coordinates[0].isMine() shouldBe false
        board.coordinates[1].isMine() shouldBe true
        board.coordinates[2].isMine() shouldBe true
        board.coordinates[3].isMine() shouldBe true
    }
}
