package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OpenResultTest {
    @Test
    @DisplayName("mine block이 오픈됐다면 LOSE를 반환한다")
    fun `sut return LOSE when mine block is open`() {
        // Arrange
        val isMineOpen = true
        val sut = OpenResult

        // Act
        val act = sut.fromMineBlockCheck(isMineOpen)

        // Assert
        assertThat(act).isEqualTo(OpenResult.LOSE)
    }

    @Test
    @DisplayName("mine block이 오픈되지 않았다면 CONTINUE를 반환한다")
    fun `sut return CONTINUE when mine block is not open`() {
        // Arrange
        val isMineOpen = false
        val sut = OpenResult

        // Act
        val act = sut.fromMineBlockCheck(isMineOpen)

        // Assert
        assertThat(act).isEqualTo(OpenResult.CONTINUE)
    }

    @Test
    @DisplayName("normal block이 모두 오픈됐다면 WIN을 반환한다")
    fun `sut return WIN when normal blocks are all open`() {
        // Arrange
        val isAllOpenNormalBlock = true
        val sut = OpenResult

        // Act
        val act = sut.fromNormalBlockCheck(isAllOpenNormalBlock)

        // Assert
        assertThat(act).isEqualTo(OpenResult.WIN)
    }

    @Test
    @DisplayName("normal block이 모두 오픈되지 않았다면 CONTINUE를 반환한다")
    fun `sut return CONTINUE when normal blocks are not all open`() {
        // Arrange
        val isAllOpenNormalBlock = false
        val sut = OpenResult

        // Act
        val act = sut.fromMineBlockCheck(isAllOpenNormalBlock)

        // Assert
        assertThat(act).isEqualTo(OpenResult.CONTINUE)
    }
}
