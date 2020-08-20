package domain

import domain.Location.Companion.toLocationOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LocationTest {

    @Test
    fun `자신을 제외한 주변 8방향의 좌표를 찾는다`() {
        val location = Location(1, 0)
        assertThat(location.findNotifyRange(3, 3)).contains(
            Location(0, 0), Location(0, 1), Location(1, 1),
            Location(2, 0), Location(2, 1)
        )
    }

    @Test
    fun `지정한 방향의 좌표를 얻는다`() {
        val location = Location(1, 1)
        assertThat(location.applyDirection(Direction.TOP, 3, 4)).isEqualTo(Location(1, 2))
        assertThat(location.applyDirection(Direction.RIGHT, 2, 2)).isEqualTo(Location(1, 1))
    }

    @ParameterizedTest
    @ValueSource(strings = ["3, 4"])
    fun `'x, y' 형식 문자열로 Location을 생성할 수 있다`(input: String) {
        assertThat(input.toLocationOrNull()).isEqualTo(Location(3, 4))
    }
}
