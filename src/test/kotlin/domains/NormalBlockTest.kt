package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class NormalBlockTest {
    @Test
    @DisplayName("오픈하면 마커를 주변 지뢰의 수로 변경한다")
    fun `sut set marker to surrounding mines when it was opened`() {
        // Arrange
        val sut = NormalBlock(Position.fromApplication(1, 1))
        val mineBlocks = listOf(
            MineBlock(position = Position.fromApplication(1, 2)),
            MineBlock(position = Position.fromApplication(2, 2)),
            MineBlock(position = Position.fromApplication(5, 5)),
        )
        sut.updateMarkerByAroundMines(mineBlocks)

        // Act
        sut.open()

        // Assert
        assertThat(sut.marker).isEqualTo("2")
    }

    @Test
    @DisplayName("주변에 지뢰가 있는지 체크")
    fun `sut check zero surrounding mines`() {
        // Arrange
        val sut = NormalBlock(Position.fromApplication(1, 1))

        // Act
        val act = sut.isZeroSurroundingMines()

        // Assert
        assertThat(act).isTrue
    }
}
