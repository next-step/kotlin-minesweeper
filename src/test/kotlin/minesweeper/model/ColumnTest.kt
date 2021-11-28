package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ColumnTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100, -150])
    fun `열(Column)는 항상 1 이상의 값을 가진다`(value: Int) {
        assertThrows<RuntimeException> { Column(value) }
    }

    @Test
    fun `Column(1)을 증가시키면 Column(2)가 된다`() {
        val column = Column(1)
        assertThat(column.increment()).isEqualTo(Column(2))
    }

    @Test
    fun `Column(2)을 감소시키면 Column(1)이 된다`() {
        val column = Column(2)
        assertThat(column.decrement()).isEqualTo(Column(1))
    }

    @Test
    fun `Column(1)을 감소시키면 Column(1)이 된다`() {
        val column = Column(1)
        assertThat(column.decrement()).isEqualTo(Column(1))
    }
}
