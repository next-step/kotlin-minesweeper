package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellTest {

    @Test
    fun `Cell은 Row와 Column을 가진다`() {
        val cell = Cell.Zero(Position(1, 1))
        assertAll(
            { assertThat(cell.row).isEqualTo(Row(1)) },
            { assertThat(cell.column).isEqualTo(Column(1)) }
        )
    }

    @Test
    fun `Number Cell은 주변에 인접한 지뢰의 개수를 가진다`() {
        val cell = Cell.Number(MineCount.valueOf(1), Position(1, 1))
        assertThat(cell.adjustMineCount).isEqualTo(MineCount.valueOf(1))
    }
}
