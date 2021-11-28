package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PositionTest {

    @Test
    fun `Position은 Row와 Column을 가진다`() {
        val position = Position.of(2, 5)
        assertAll(
            { assertThat(position.row).isEqualTo(Row(2)) },
            { assertThat(position.column).isEqualTo(Column(5)) }
        )
    }
}
