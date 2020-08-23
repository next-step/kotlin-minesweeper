package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MineSweeperGameTest {
    @DisplayName("높이와 너비의 곱보다 지뢰의 갯수가 적으면 true, 아니면 false를 반환한다.")
    @Test
    fun validateMineCount() {
        val func = MineSweeperGame.validateMineCount(20)
        assertAll(
            { assertThat(func(10)).isTrue() },
            { assertThat(func(25)).isFalse() }
        )
    }
}
