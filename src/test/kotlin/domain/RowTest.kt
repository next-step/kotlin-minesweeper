package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class RowTest {
    @Test
    fun `Row는 한 줄에 나열될 Cell들을 보관한다`() {
        val row = Row(10) {
            Cell.Land.ZERO
        }

        assertAll(
            { assertThat(row).hasSize(10) },
            { assertThat(row).allMatch { cell -> cell == Cell.Land.ZERO } }
        )
    }
}
