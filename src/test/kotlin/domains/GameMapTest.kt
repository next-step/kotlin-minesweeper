package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GameMapTest {
    @Test
    @DisplayName("게임 사이즈를 통해서 게임 맵을 생성한다")
    fun `sut generate game map by gameSize`() {
        // Arrange
        val gameSize = GameSize(10, 10)
        val minePositions = Positions(
            listOf(
                Position.fromApplication(0, 0),
                Position.fromApplication(1, 2),
                Position.fromApplication(3, 5),
                Position.fromApplication(4, 7),
                Position.fromApplication(8, 8),
            )
        )
        val sut = GameMap

        // Act
        val actual = sut.from(gameSize = gameSize, minePositions = minePositions)

        // Assert
        assertThat(actual.blocks.values.size).isEqualTo(100)
    }
}
