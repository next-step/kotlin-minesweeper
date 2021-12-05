package minesweeper.domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PositionTest {

    @Test
    fun `위치를 생성할 수 있다`() {
        assertThat(Position(1, 10)).isNotNull
    }
}
