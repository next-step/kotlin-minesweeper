package domain

import domain.block.Land
import domain.block.Mine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RowTest {
    @Test
    internal fun `Row내 Mine을 제외한 Land의 cell이 열려있으면 true를 반환한다`() {
        // given
        val row = Row(
            listOf(
                Land(0, true),
                Land(1, true),
                Mine(),
            )
        )

        // when
        val allOpened = row.isAllOpened()

        // then
        assertThat(allOpened).isTrue
    }

    @Test
    internal fun `Row내 Mine을 제외한 모든 cell이 하나라도 닫혀 있으면 false를 반환한다`() {
        // given
        val row = Row(
            listOf(
                Land(0, false),
                Land(1, true),
                Mine()
            )
        )

        // when
        val allOpened = row.isAllOpened()

        // then
        assertThat(allOpened).isFalse
    }
}
