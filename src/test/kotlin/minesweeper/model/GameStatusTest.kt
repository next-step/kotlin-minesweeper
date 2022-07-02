package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("게임 상태 테스트")
class GameStatusTest {

    @Test
    fun `ONGOING 객체 여부 판단하는 기능이 정상 동작`() {
        // given, when, then
        assertAll(
            "determining whether object is ONGOING",
            { assertThat(GameStatus.ONGOING.isOngoing()).isTrue },
            { assertThat(GameStatus.WIN.isOngoing()).isFalse },
            { assertThat(GameStatus.LOST.isOngoing()).isFalse },
        )
    }

    @Test
    fun `WIN 객체 여부 판단하는 기능이 정상 동작`() {
        // given, when, then
        assertAll(
            "determining whether object is WIN",
            { assertThat(GameStatus.ONGOING.win()).isFalse },
            { assertThat(GameStatus.WIN.win()).isTrue },
            { assertThat(GameStatus.LOST.win()).isFalse },
        )
    }

    @Test
    fun `LOST 객체 여부 판단하는 기능이 정상 동작`() {
        // given, when, then
        assertAll(
            "determining whether object is LOST",
            { assertThat(GameStatus.ONGOING.lost()).isFalse },
            { assertThat(GameStatus.WIN.lost()).isFalse },
            { assertThat(GameStatus.LOST.lost()).isTrue },
        )
    }
}
