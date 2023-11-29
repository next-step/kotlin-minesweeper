package minesweeper.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineMapMetaTest {
    @ParameterizedTest
    @CsvSource(
        "0 0 0",
        "1 0 0",
        "1 1 -1",
        "0 -1 -1"
    )
    fun `MineMapMeta 프로퍼티는 0이거나 음수일 수 없다`(input: String) {
        // given
        val properties = input.split(" ").map { it.toInt() }

        assertThrows<IllegalArgumentException> { // then
            MineMapMeta( // when
                height = properties[0],
                width = properties[1],
                mineCount = properties[2]
            )
        }
    }

    @ParameterizedTest
    @CsvSource(
        "1 1 2",
        "10 1 11",
        "10 10 101"
    )
    fun `지뢰 개수는 MineMap을 구성하는 셀 크기(height*width)를 초과할 수 없다`(input: String) {
        // given
        val properties = input.split(" ").map { it.toInt() }

        assertThrows<IllegalArgumentException> { // then
            MineMapMeta( // when
                height = properties[0],
                width = properties[1],
                mineCount = properties[2]
            )
        }
    }

    @ParameterizedTest
    @CsvSource(
        "1 1 1, 1",
        "1 10 5, 10",
        "10 30 50, 300"
    )
    fun `MineMap을 구성하는 Cell 개수를 반환한다`(input: String, expected: Int) {
        // given
        val properties = input.split(" ").map { it.toInt() }
        val mineMapMeta = MineMapMeta(
            height = properties[0],
            width = properties[1],
            mineCount = properties[2]
        )

        // when
        val cellCount = mineMapMeta.getCellCount()

        // then
        assertEquals(expected, cellCount)
    }
}
