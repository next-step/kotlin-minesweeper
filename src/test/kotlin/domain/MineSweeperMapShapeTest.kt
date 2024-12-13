package domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class MineSweeperMapShapeTest {
    @Test
    fun `너비 높이는 양수여야한다`() {
        assertThatThrownBy { MineSweeperMapShape(0, 1) }
            .isInstanceOf(IllegalArgumentException::class.java).hasMessage("너비는 양수여야함")

        assertThatThrownBy { MineSweeperMapShape(1, -1) }
            .isInstanceOf(IllegalArgumentException::class.java).hasMessage("높이는 양수여야함")
    }
}
