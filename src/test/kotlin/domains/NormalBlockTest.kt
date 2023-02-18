package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class NormalBlockTest {
    @Test
    @DisplayName("근처에 지뢰 수를 주어진 지뢰 블럭으로 카운트해서 marker를 변경한다")
    fun `sut should update marker when mineBlocks are given`() {
        // Arrange
        val sut = NormalBlock(Position(1, 1))
        val mineBlocks = listOf(
            MineBlock(position = Position(1, 2)),
            MineBlock(position = Position(2, 2)),
            MineBlock(position = Position(5, 5)),
        )

        // Act
        sut.updateMarkerByAroundMines(mineBlocks = mineBlocks)

        // Assert
        assertThat(sut.marker).isEqualTo("2")
    }
}
