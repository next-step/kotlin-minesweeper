package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RowTest {
    @Test
    internal fun `지정한 범위만큼 Row가 생성된다`() {
        // given
        val range = (0 until 10)

        // when
        val row = Row.create(range)

        // then
        assertThat(row.cells).hasSize(10)
    }
}
