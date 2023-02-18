package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlocksTest {
    @Test
    @DisplayName("주어진 position에 있는 block을 찾아준다")
    fun `sut get block when position is given`() {
        // Arrange
        val normalPosition = Position(1, 2)
        val normalBlock = NormalBlock(normalPosition)

        val minePosition = Position(10, 10)
        val mineBlock = MineBlock(minePosition)

        val sut = Blocks(listOf(normalBlock, mineBlock))

        // Act
        val act = sut.getBlockByPosition(normalPosition)

        // Assert
        assertThat(act.position).isEqualTo(normalPosition)
    }

    @Test
    @DisplayName("normal blocks만 찾아준다")
    fun `sut return normal blocks`() {
        // Arrange
        val normalPositionA = Position(1, 2)
        val normalBlockA = NormalBlock(normalPositionA)

        val normalPositionB = Position(4, 2)
        val normalBlockB = NormalBlock(normalPositionB)

        val minePosition = Position(10, 10)
        val mineBlock = MineBlock(minePosition)

        val sut = Blocks(listOf(normalBlockA, normalBlockB, mineBlock))

        // Act
        val act = sut.findNormalBlocks()

        // Assert
        assertThat(act).isEqualTo(listOf(normalBlockA, normalBlockB))
    }

    @Test
    @DisplayName("mine blocks만 찾아준다")
    fun `sut return mine blocks`() {
        // Arrange
        val normalPositionA = Position(1, 2)
        val normalBlockA = NormalBlock(normalPositionA)

        val minePositionA = Position(10, 10)
        val mineBlockA = MineBlock(minePositionA)

        val minePositionB = Position(3, 3)
        val mineBlockB = MineBlock(minePositionB)

        val sut = Blocks(listOf(normalBlockA, mineBlockA, mineBlockB))

        // Act
        val act = sut.findMineBlocks()

        // Assert
        assertThat(act).isEqualTo(listOf(mineBlockA, mineBlockB))
    }
}
