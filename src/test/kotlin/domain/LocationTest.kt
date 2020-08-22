package domain

import domain.Location.Companion.toLocationOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LocationTest {

    @ParameterizedTest
    @ValueSource(strings = ["3, 4"])
    fun `'x, y' 형식 문자열로 Location을 생성할 수 있다`(input: String) {
        assertThat(input.toLocationOrNull()).isEqualTo(Location(3, 4))
    }

    @ParameterizedTest
    @ValueSource(strings = ["3, a"])
    fun `'유효하지 않은 문자열을 사용하면 null을 반환한다`(input: String) {
        assertThat(input.toLocationOrNull()).isNull()
    }
}
