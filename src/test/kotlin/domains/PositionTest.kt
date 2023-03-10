package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PositionTest {
    @Test
    @DisplayName("게임 사이즈에 맞는 사용할 수 있는 주변 포지션을 조회한다")
    fun `sut list up positions when game size is given`() {
        // Arrange
        val gameSize = GameSize(10, 10)
        val sut = Position.fromApplication(0, 5)

        // Act
        val act = sut.getSurroundingPositions(gameSize)

        // Assert
        assertThat(act.values.size).isEqualTo(5)
    }
}
