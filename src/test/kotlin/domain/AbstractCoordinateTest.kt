package domain

import domain.coord.AbstractCoordinate
import domain.coord.RelativeCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class AbstractCoordinateTest {

    @ParameterizedTest
    @CsvSource(value = ["0, 0, -1, 1", "0, 0, 0, -1"])
    internal fun `y 또는 x 좌표의 합중 하나라도 음수이면 두 좌표는 더할 수 없다`(y1: Int, x1: Int, y2: Int, x2: Int) {
        // given
        val coordinate1 = AbstractCoordinate(y1, x1)
        val coordinate2 = RelativeCoordinate(y2, x2)

        // when
        val isPossiblePlus = coordinate1.isPossiblePlus(coordinate2)

        // then
        assertThat(isPossiblePlus).isFalse
    }

    @Test
    internal fun `y 와 x 좌표의 각 합 모두 양수이면 두 좌표는 더할 수 있다`() {
        // given
        val coordinate1 = AbstractCoordinate(0, 0)
        val coordinate2 = AbstractCoordinate(1, 1)

        // when
        val isPossiblePlus = coordinate1.isPossiblePlus(coordinate2)

        // then
        assertThat(isPossiblePlus).isTrue
    }
}
