package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("지뢰 지도의 설정값")
internal class MineMapConfigTest {

    @Test
    fun `생성자 인자로 설정 객체 생성`() {
        val expectedHeight = 10
        val expectedWidth = 10
        val config = MapConfig(expectedHeight, expectedWidth)
        assertThat(config.height).isEqualTo(expectedHeight)
        assertThat(config.width).isEqualTo(expectedWidth)
    }

    @Test
    fun `올바르지 않은 설정 값은 예외`() {
        assertThrows<IllegalArgumentException> { MapConfig(-1, 2) }
    }
}
