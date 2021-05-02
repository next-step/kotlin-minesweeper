package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameStateTest {

    @Test
    fun `Running 상태는 게임진행중이다`() {
        val running = GameState.RUNNING
        assertThat(running.isRunning()).isTrue()
    }

    @Test
    fun `Win, Lose 상태는 게임진행중이 아니다`() {
        val win = GameState.WIN
        val lose = GameState.LOSE

        assertThat(win.isRunning()).isFalse()
        assertThat(lose.isRunning()).isFalse()
    }
}
