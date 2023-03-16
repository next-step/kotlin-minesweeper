package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineBlockTest {
    @Test
    @DisplayName("지뢰 블럭을 오픈하면 isOpend 상태가 바뀐다")
    fun `sut change isOpened when it was opened`() {
        // Arrange
        val sut = MineBlock(position = Position.fromApplication(1, 1))

        // Act
        sut.open()

        // Assert
        assertThat(sut.isOpened).isTrue
    }
}
