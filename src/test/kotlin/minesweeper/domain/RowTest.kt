package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RowTest {
    @Test
    fun `지뢰찾기 보드의 한 행을 생성한다`() {
        assertDoesNotThrow { Row(3) }
    }

    @ValueSource(ints = [0, -1])
    @ParameterizedTest
    fun `지뢰찾기 보드의 한 행을 생성할 때 0보다 작은 값이 들어오면 예외가 발생한다`(col: Int) {
        assertThrows<IllegalArgumentException> { Row(col) }
    }

    @Test
    fun `지뢰찾기 보드의 한 행을 생성할 때 지뢰를 심는다`() {
        val row = Row(3)
        row.setMine(1)
        row.countMines() shouldBe 1
    }

    @Test
    fun `지뢰찾기 보드의 한 행을 생성할 때 지뢰를 심을 때 열의 범위를 벗어나면 예외가 발생한다`() {
        val row = Row(3)
        assertThrows<IllegalArgumentException> { row.setMine(3) }
    }
}
