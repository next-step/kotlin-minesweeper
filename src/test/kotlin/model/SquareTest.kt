package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SquareTest() {
    @Test
    @DisplayName("지뢰 게임을 위한 플레이트를 생성한다")
    fun `make`() {
        val width = Number(5)
        val height = Number(5)
        val square = Square(width, height)
        val minePlate = square.make()
        assertThat(minePlate.value.size).isEqualTo(height.value)
        assertThat(minePlate.value[0].size).isEqualTo(width.value)
    }
}
