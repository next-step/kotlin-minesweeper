package minesweeper.model.coordinate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BoardAreaTest {

    @ParameterizedTest
    @CsvSource(
        "0,0",
        "0,1",
        "1,-0",
        "-1,1",
        "1,-1"
    )
    fun `BoardArea 실패 케이스 테스트`(rowCount: Int, columnCount: Int) {

        assertThrows<IllegalArgumentException> { BoardArea.of(rowCount, columnCount) }
    }

    @ParameterizedTest
    @CsvSource(
        "1,1",
        "10,5",
        "100,100",
        "1000,1000"
    )
    fun `BoardArea 성공 케이스 테스트`(rowCount: Int, columnCount: Int) {

        val expectedCellCount = rowCount * columnCount

        // when
        val actualArea = BoardArea.of(rowCount, columnCount).cellCount

        // then
        assertThat(actualArea).isEqualTo(expectedCellCount)
    }
}
