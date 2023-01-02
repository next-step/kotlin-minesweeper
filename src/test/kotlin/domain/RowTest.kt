package domain

import domain.block.Land
import domain.block.Mine
import domain.pos.AbstractPos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class RowTest {

    @Test
    internal fun `지정된 블록을 가져온다`() {
        // given
        val row = Row(
            listOf(
                Land(0, true),
                Land(1, true),
                Mine(),
            )
        )
        // when
        val block = row.blockOf(AbstractPos.of(2))

        // then
        assertThat(block).isInstanceOf(Mine::class.java)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2])
    internal fun `입력 Row의 크기 이내에 있으면 true를 반환한다`(input: Int) {

        // given
        val row = Row(
            listOf(
                Land(0, true),
                Land(1, true),
                Mine(),
            )
        )
        // when, then
        assertThat(row.isRangeLessThen(input)).isTrue
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 4, 5])
    internal fun `입력 Row의 크기 보다 크면 false를 반환한다`(input: Int) {

        // given
        val row = Row(
            listOf(
                Land(0, true),
                Land(1, true),
                Mine(),
            )
        )
        // when, then
        assertThat(row.isRangeLessThen(input)).isFalse
    }

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
