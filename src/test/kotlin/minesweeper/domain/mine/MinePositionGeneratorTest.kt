package minesweeper.domain.mine

import io.kotest.matchers.shouldBe
import minesweeper.domain.board.BoardRange
import org.junit.jupiter.api.Test

class MinePositionGeneratorTest {

    @Test
    fun `지뢰 개수만큼 지뢰 위치를 중복없이 랜덤하게 생성`() {
        val mineQuantity = 3
        val boardRange = BoardRange(height = 3, width = 3)

        val minePositions =
            MinePositionGenerator.createMinePositions(mineQuantity = mineQuantity, boardRange = boardRange)

        minePositions.size shouldBe mineQuantity
    }
}
