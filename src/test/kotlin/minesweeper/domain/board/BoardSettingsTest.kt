package minesweeper.domain.board

import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.ThrowableAssert
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BoardSettingsTest {

    @ParameterizedTest
    @CsvSource(value = ["1,1,2", "10,10,1000", "5,5,26"])
    fun `주어진 영역보다 많은 수의 지뢰를 생성할 수 없다`(width: Int, height: Int, mineCounts: Int) {
        // when
        val callable = ThrowableAssert.ThrowingCallable { BoardSettings(width, height, mineCounts) }

        // then
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy(callable)
            .withMessageMatching("주어진 너비와 높이보다 많은 개수의 지뢰를 입력받을 수 없습니다.")
    }
}
