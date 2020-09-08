package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClosePointTest {
    @Test
    fun make_close_point() {
        val closePoint = ClosePoint(Coordinate(0, 0), false)

        assertThat(closePoint.isMine()).isNull()
        assertThat(closePoint.isOpen()).isFalse()
    }

    @Test
    fun open_not_mine() {
        val closePoint = ClosePoint(Coordinate(0, 0), false)

        val openPoint = closePoint.open()

        assertThat(openPoint.isMine()).isFalse()
        assertThat(openPoint.isOpen()).isTrue()
    }

    @Test
    fun open_mine() {
        val closePoint = ClosePoint(Coordinate(0, 0), true)

        val openPoint = closePoint.open()

        assertThat(openPoint.isMine()).isTrue()
        assertThat(openPoint.isOpen()).isTrue()
    }
}
