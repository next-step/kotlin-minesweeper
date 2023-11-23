package minesweeper.model.board

import io.kotest.matchers.shouldBe
import minesweeper.model.cell.Island
import minesweeper.model.cell.Mine
import minesweeper.model.cell.Opening
import org.junit.jupiter.api.Test

class MinefieldTest {
    @Test
    fun `지뢰찾기 맵 생성 결과 지뢰 갯수만큼 지뢰가 심어진다`() {
        // given
        val openings: Array<Array<Opening>> = arrayOf(
            arrayOf(Island(), Mine(), Island()),
            arrayOf(Mine(), Island(), Mine()),
            arrayOf(Mine(), Island(), Island())
        )
        val minefield = Minefield(3, 3, openings)

        // when
        val (islandCount, mineCount) = openings.flatten().partition { it is Island }

        // then
        islandCount.size shouldBe 5
        mineCount.size shouldBe 4
    }
}
