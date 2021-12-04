package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellTest {

    private val position: Position = Position(1, 1)

    @Test
    fun `Row와 Column을 가진다`() {
        val cell = Cell.zero(position)
        assertAll(
            { assertThat(cell.row).isEqualTo(Row(1)) },
            { assertThat(cell.column).isEqualTo(Column(1)) }
        )
    }

    @Test
    fun `Number는 주변에 인접한 지뢰의 개수를 가진다`() {
        val cell = Cell.Number(MineCount.valueOf(1), position)
        assertThat(cell.adjustMineCount).isEqualTo(MineCount.valueOf(1))
    }

    @Test
    fun `Number의 인접한 지뢰 개수를 증가시킬 수 있다`() {
        val cell = Cell.Number(MineCount.valueOf(1), position)
        val actual = cell.increment()

        assertThat(actual).isEqualTo(
            Cell.Number(MineCount.valueOf(2), position)
        )
    }

    @Test
    fun `Mine은 항상 숨겨져 있다`() {
        // given
        val cell = Cell.Mine(position)

        // when & then
        assertThat(cell.isVisible).isFalse
    }

    @Test
    fun `Number를 열면 열려있는 상태가 된다`() {
        // given
        val cell = Cell.zero(position)

        // when
        val actual = cell.tryOpen()

        // then
        assertThat(actual.isVisible).isTrue
    }
}
