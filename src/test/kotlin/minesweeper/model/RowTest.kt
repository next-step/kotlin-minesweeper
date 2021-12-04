package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RowTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100, -150])
    fun `행(Row)는 항상 1 이상의 값을 가진다`(value: Int) {
        assertThrows<RuntimeException> { Row(value) }
    }

    @Test
    fun `Row(1)를 증가시키면 Row(2)가 된다`() {
        val row = Row(1)
        assertThat(row.increment()).isEqualTo(Row(2))
    }

    @Test
    fun `Row(2)를 감소시키면 Row(1)이 된다`() {
        val row = Row(2)
        assertThat(row.decrement()).isEqualTo(Row(1))
    }

    @Test
    fun `Row(1)를 감소시키면 Row(1)이 된다`() {
        val row = Row(1)
        assertThat(row.decrement()).isEqualTo(Row(1))
    }
}
