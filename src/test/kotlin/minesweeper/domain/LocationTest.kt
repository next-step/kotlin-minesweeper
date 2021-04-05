package minesweeper.domain

import minesweeper.domain.Location
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class LocationTest {

    @ParameterizedTest
    @NullAndEmptySource
    fun `빈 값을 입력하면 예외가 발생한다`(location: String?) {
        assertThrows<IllegalArgumentException> { Location.of(location) }
    }

    @Test
    fun `숫자 2개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Location.of("1,2,3") }
    }

    @Test
    fun `숫자와 콤마 이외의 값이 들어오면 예외가 발생한다`() {
        assertThrows<NumberFormatException> { Location.of("1,ㅁ") }
    }

    @Test
    fun `숫자 2개를 올바르게 입력하여 인덱스로 convert 할 수 있다`() {
        assertThat(Location.of("1, 2").getConvertIndex(5)).isEqualTo(7)
    }
}
