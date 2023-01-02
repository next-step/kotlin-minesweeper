package minesweeper.domain

import minesweeper.model.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BlockTest {

    @ParameterizedTest
    @CsvSource(value = ["-1:10", "-10:1"], delimiter = ':')
    fun failToRequireBlockPointX(pointX: Int, pointY: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            Block(Point(pointX, pointY))
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["10:-1", "1:-10"], delimiter = ':')
    fun failToRequireBlockPointY(pointX: Int, pointY: Int) {
        assertThrows(IllegalArgumentException::class.java) {
            Block(Point(pointX, pointY))
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["1:10", "10:1"], delimiter = ':')
    fun requireBlockPoint(pointX: Int, pointY: Int) {
        val block = Block(Point(pointX, pointY))
        assertThat(block.point.x).isEqualTo(pointX)
        assertThat(block.point.y).isEqualTo(pointY)
    }

    @Test
    fun checkOpen() {
        val block = Block(Point(0, 0))
        assertThat(block.isOpen()).isFalse
        block.open()
        assertThat(block.isOpen()).isTrue
    }

    @Test
    fun checkMine() {
        val block = Block(Point(0, 0))
        assertThat(block.isMine()).isFalse
        block.mine()
        assertThat(block.isMine()).isTrue
    }
}
