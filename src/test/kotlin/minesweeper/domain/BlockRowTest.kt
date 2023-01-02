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
    fun `xIndex, row length 둘중 하나가 1개 미만으로 입력시 에러가 발생한다`(xIndex: Int, length: Int) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            BlockRow(xIndex, length)
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

    @Test
    fun `Block 리스트의 블록들이 전체 열린 상태인지 확인할 수 있다`() {
        val first = Block(Point(0, 0)).also { it.open() }
        val second = Block(Point(0, 1)).also { it.open() }
        val blocks = listOf(first, second)
        val blockRow = BlockRow(blocks)

        assertThat(blockRow.allOpen()).isTrue
    }

    @Test
    fun `Block 리스트의 블록 중 하나라도 열리지 않으면 전체 열린 상태가 아니다`() {
        val first = Block(Point(0, 0)).also { it.open() }
        val second = Block(Point(0, 1))
        val blocks = listOf(first, second)
        val blockRow = BlockRow(blocks)

        assertThat(blockRow.allOpen()).isFalse
    }
}
