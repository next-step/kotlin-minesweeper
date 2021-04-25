package domain.position

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PositionIdTest {

    @ParameterizedTest
    @CsvSource(value = ["0,0,0", "1,0,1", "13,1,3", "19,1,9"])
    fun `positionId를 position으로 변경한다`(positionIdNumber: Int, row: Int, col: Int) {
        val width = 10
        val position = PositionId(positionIdNumber).position(width)

        Assertions.assertThat(position.row).isEqualTo(row)
        Assertions.assertThat(position.col).isEqualTo(col)
    }
}
