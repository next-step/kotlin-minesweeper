package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

import org.junit.jupiter.api.assertThrows

internal class BoardSpecTest {

    @Test
    fun `마인의 수는 전체 셀의 수를 넘을수 없다`() {
        assertDoesNotThrow { BoardSpec(NaturalNumber(2), NaturalNumber(2), NaturalNumber(4)) }
        assertThrows<IllegalArgumentException>("invalid mine count") { BoardSpec(NaturalNumber(2), NaturalNumber(2), NaturalNumber(5)) }
    }
}
