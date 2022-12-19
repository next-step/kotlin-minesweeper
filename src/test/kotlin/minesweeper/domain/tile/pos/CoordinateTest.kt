package minesweeper.domain.tile.pos

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CoordinateTest {
    @ParameterizedTest
    @CsvSource(value = ["1,1:1,1:true", "1,1:2,2:false"], delimiter = ':')
    fun `Coordinate - 좌표 위치 동일 여부 확인 테스트`(given1: String, given2: String, expected: Boolean) {
        // given
        val (x1, y1) = given1.split(",").map { it.toInt() }
        val (x2, y2) = given2.split(",").map { it.toInt() }
        val coordinate1 = Coordinate(Position(x1), Position(y1))
        val coordinate2 = Coordinate(Position(x2), Position(y2))

        // when
        val actual = coordinate1 == coordinate2

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
