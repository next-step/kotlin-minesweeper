package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰 지도 생성")
internal class MineMapTest {

    @Test
    fun `높이와 너비 만큼 맵 그리기`() {
        val config = MapConfig(5, 10)
        val map = MineMap.create(config)
        assertThat(map.rows()).hasSize(5)
        assertThat(map.rows().first().fragments()).hasSize(10)
    }
}
