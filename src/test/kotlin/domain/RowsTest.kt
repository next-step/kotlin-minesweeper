package domain

import domain.block.Land
import domain.block.Mine
import domain.coord.AbstractCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class RowsTest {

    @Test
    internal fun `좌표에 맞는 블록을 가져온다`() {
        // given
        val rows = Rows(
            Row(Land(0, true)),
            Row(Mine())
        )
        // when
        val block = rows.blockOf(AbstractCoordinate(1, 0))

        // then
        assertThat(block).isInstanceOf(Mine::class.java)
    }

    @Test
    internal fun `마인을 제외한 모든 블록이 열려있으면 true를 반환한다`() {
        // given
        val rows = Rows(
            Row(
                Land(0, true),
                Land(0, true),
                Land(0, true),
            ),
            Row(
                Mine(),
                Land(0, true),
                Land(0, true),
            )
        )
        // when
        val isAllOpened = rows.isAllOpened()

        // then
        assertThat(isAllOpened).isTrue
    }

    @Test
    internal fun `마인을 제외한 모든 블록중 하나라도 닫혀있으면 false를 반환한다`() {
        // given
        val rows = Rows(
            Row(
                Land(0, false),
                Land(0, true),
                Land(0, true),
            ),
            Row(
                Mine(),
                Land(0, true),
                Land(0, true),
            )
        )
        // when
        val isAllOpened = rows.isAllOpened()

        // then
        assertThat(isAllOpened).isFalse
    }

    @Test
    internal fun `블록의 Row 수보다 이내이면 true를 반환한다`() {
        // given
        val rows = Rows(
            Row(
                Land(0, false),
                Land(0, true),
                Land(0, true),
            ),
            Row(
                Mine(),
                Land(0, true),
                Land(0, true),
            )
        )
        // when
        val isRangeRowOf = rows.isRangeRowOf(1)

        // then
        assertThat(isRangeRowOf).isTrue
    }

    @Test
    internal fun `블록의 Row 수보다 크면 false를 반환한다`() {
        // given
        val rows = Rows(
            Row(
                Land(0, false),
                Land(0, true),
                Land(0, true),
            ),
            Row(
                Mine(),
                Land(0, true),
                Land(0, true),
            )
        )
        // when
        val isRangeRowOf = rows.isRangeRowOf(2)

        // then
        assertThat(isRangeRowOf).isFalse
    }

    @Test
    internal fun `비어있는 Rows 에서 cell 갯수를 가져오려면 예외가 발생한다`() {
        // given
        val rows = Rows()
        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            rows.isRangeCellOf(1)
        }
    }

    @Test
    internal fun `블록의 Cell 수보다 작으면 true를 반환한다`() {
        // given
        val rows = Rows(
            Row(
                Land(0, false),
                Land(0, true),
                Land(0, true),
            ),
            Row(
                Mine(),
                Land(0, true),
                Land(0, true),
            )
        )
        // when
        val isRangeCellOf = rows.isRangeCellOf(2)

        // then
        assertThat(isRangeCellOf).isTrue
    }

    @Test
    internal fun `블록의 Cell 수와 같거나 크면 false를 반환한다`() {
        // given
        val rows = Rows(
            Row(
                Land(0, false),
                Land(0, true),
                Land(0, true),
            ),
            Row(
                Mine(),
                Land(0, true),
                Land(0, true),
            )
        )
        // when
        val isRangeCellOf = rows.isRangeCellOf(3)

        // then
        assertThat(isRangeCellOf).isFalse
    }
}
