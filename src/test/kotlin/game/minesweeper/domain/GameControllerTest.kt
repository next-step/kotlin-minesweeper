package game.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("맵 생성")
internal class GameControllerTest {

    @Test
    fun `높이와 너비 만큼 맵 그리기`() {
        val gameController = GameController(5, 10, 10)
        assertThat(gameController.rows).hasSize(5)
        assertThat(gameController.rows[0].fragments()).hasSize(10)
    }
}
