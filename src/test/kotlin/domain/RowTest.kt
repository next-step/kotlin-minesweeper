package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class RowTest {
    @Test
    fun `Row는 한 줄에 나열될 Cell들을 보관한다`() {
        val cells = List(10) {
            Cell.values().random()
        }
        val row = Row(cells)

        assertAll(
            { assertThat(row).hasSize(cells.size) },
            { assertThat(row).containsAll(cells) }
        )
    }
}
