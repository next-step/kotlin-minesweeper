package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun `주어진 범위 안에서 인접 좌표를 반환한다`() {
        // 주변이 모두 셀로 감싸진 경우
        var position = Position(1, 1)
        var expected = listOf(
            Position(0, 0),
            Position(0, 1),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 0),
            Position(2, 1),
            Position(2, 2),
        )

        assertThat(position.getArounds(3, 3)).containsExactlyInAnyOrderElementsOf(expected)

        // 모퉁이에 위치한 셀인 경우
        position = Position(2, 2)
        expected = listOf(
            Position(1, 1),
            Position(1, 2),
            Position(2, 1),
        )

        assertThat(position.getArounds(3, 3)).containsExactlyInAnyOrderElementsOf(expected)
    }
}
