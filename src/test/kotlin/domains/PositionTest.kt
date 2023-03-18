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

    @Test
    @DisplayName("사용자에게 입력값을 받아서 생성한다")
    fun `sut create position when user input data`() {
        // Arrange
        val sut = Position

        // Act
        val act = sut.fromUserInput(10, 5)

        // Assert
        assertThat(act.x).isEqualTo(9)
        assertThat(act.y).isEqualTo(4)
    }

    @Test
    @DisplayName("앱에서 생성한다")
    fun `sut create position by application`() {
        // Arrange
        val sut = Position

        // Act
        val act = sut.fromApplication(10, 5)

        // Assert
        assertThat(act.x).isEqualTo(10)
        assertThat(act.y).isEqualTo(5)
    }
}
