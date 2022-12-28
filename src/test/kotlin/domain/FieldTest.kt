package domain

import domain.block.Land
import domain.coord.AbstractCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class FieldTest {

    @ParameterizedTest
    @CsvSource(value = ["-1, 0", "0, -1", "10, 5", "5, 10"])
    internal fun `필드의 크기를 넘는 좌표를 열면 예외가 발생한다`(y: Int, x: Int) {
        // given
        val field = Field.create(10, 10, LocationOfMines(listOf()))

        // when, then
        assertThrows<IllegalArgumentException> { field.openBlock(y, x) }
    }

    @Test
    internal fun `지뢰를 열면 게임이 중단된다`() {
        // given
        val field = Field.create(10, 10, LocationOfMines(listOf(AbstractCoordinate(1, 2))))

        // when
        val gameStatus = field.openBlock(1, 2)

        // then
        assertThat(gameStatus.isProgressing).isFalse
    }

    /**
     * |   C C C C
     * |   C * * C
     * |   C C * C
     * |   C C C C
     */
    @Test
    internal fun `지뢰가 아닌 땅을 열면, 누른 땅과 지뢰가 없는 인접한 칸이 모두 열린다`() {
        // given
        val field = Field.create(
            4, 4,
            LocationOfMines(
                listOf(
                    AbstractCoordinate(1, 1),
                    AbstractCoordinate(1, 2),
                    AbstractCoordinate(2, 2)
                )
            )
        )

        // when
        field.openBlock(2, 1)

        // then
        assertAll(
            { assertThat((field.rows[0].cells[0] as Land).isOpened).isFalse },
            { assertThat((field.rows[1].cells[0] as Land).isOpened).isTrue },
            { assertThat((field.rows[2].cells[0] as Land).isOpened).isTrue },
            { assertThat((field.rows[2].cells[1] as Land).isOpened).isTrue },
            { assertThat((field.rows[3].cells[0] as Land).isOpened).isTrue },
            { assertThat((field.rows[3].cells[1] as Land).isOpened).isTrue },
            { assertThat((field.rows[3].cells[2] as Land).isOpened).isTrue },
            { assertThat((field.rows[3].cells[3] as Land).isOpened).isFalse },
        )
    }
}
