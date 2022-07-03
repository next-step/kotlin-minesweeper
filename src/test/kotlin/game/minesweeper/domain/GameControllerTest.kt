package game.minesweeper.domain

import game.minesweeper.domain.map.MapConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("맵 생성")
internal class GameControllerTest {

    @Test
    fun `높이와 너비 만큼 맵 그리기`() {
        val config = MapConfig(5, 10)
        val controller = GameController(config, 3)
        assertThat(controller.rows).hasSize(5)
        assertThat(controller.rows[0].fragments()).hasSize(10)
    }
}
