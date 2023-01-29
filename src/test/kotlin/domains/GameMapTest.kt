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
                Position(0, 0),
                Position(1, 2),
                Position(3, 5),
                Position(4, 7),
                Position(8, 8),
            )
        )
        val sut = GameMap

        // Act
        val actual = sut.from(gameSize = gameSize, minePositions = minePositions)

        // Assert
        assertThat(actual.value.size).isEqualTo(100)
    }
}
