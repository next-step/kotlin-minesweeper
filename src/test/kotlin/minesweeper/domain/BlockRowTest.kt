package minesweeper.domain

import minesweeper.model.Point
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BlockRowTest {
    @ParameterizedTest
    @CsvSource(value = ["1:-1", "-1:1"], delimiter = ':')
    fun `너비, 높이 둘중 하나가 1개 미만으로 입력시 에러가 발생한다`(width: Int, height: Int) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            BlockRow(width, height)
        }
    }

    @Test
    fun `0개 이상의 Block리스트를 포함해야 한다`() {
        val blocks = listOf(Block(Point(0, 0)), Block(Point(0, 1)))
        val blockRow = BlockRow(blocks)

        assertThat(blockRow).isNotNull
        assertThat(blockRow.blocks.size).isEqualTo(blocks.size)
    }

    @Test
    fun `1개 이상의 Block리스트를 포함하지 않으면 에러가 발생한다`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            BlockRow(emptyList())
        }
    }
}
