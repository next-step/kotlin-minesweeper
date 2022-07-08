package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MinesweeperTest {
    @Test
    fun `Minesweeper는 지뢰찾기 게임의 현황을 구성한다`() {
        val row = MutableList<Cell>(10) {
            Cell.Land.ZERO
        }
        val rows = List(10) {
            Row(row)
        }
        val minesweeper = Minesweeper(rows)

        assertAll(
            { assertThat(minesweeper).hasSize(rows.size) },
            {
                assertThat(minesweeper).allMatch {
                    it.size == row.size && it.containsAll(row)
                }
            }
        )
    }
}
