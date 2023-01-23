package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MinePositionGeneratorTest {
    @Test
    @DisplayName("게임 사이즈와 지뢰 수를 받아서 랜덤으로 중복없는 지뢰 위치 생성")
    fun `sut generateMinePositions by gameSize with mineCount`() {
        // Arrange
        val gameSize = GameSize(10, 10)
        val mineCount = 10
        val minePositionGenerator = MinePositionGenerator(gameSize, mineCount)

        // Act
        val sut = minePositionGenerator.generateMinePositions()

        // Assert
        assertThat(sut.values.size).isEqualTo(mineCount)
        assertThat(sut.values.distinct().size).isEqualTo(mineCount)
    }
}
