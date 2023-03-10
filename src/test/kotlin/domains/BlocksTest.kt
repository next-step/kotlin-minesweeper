package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlocksTest {
    @Test
    @DisplayName("mine block을 열었는지 확인")
    fun `sut check opened mine block`() {
        // Arrange
        val normalPositionA = Position.fromApplication(1, 2)
        val normalBlockA = NormalBlock(normalPositionA)

        val normalPositionB = Position.fromApplication(4, 2)
        val normalBlockB = NormalBlock(normalPositionB)

        val minePosition = Position.fromApplication(10, 10)
        val mineBlock = MineBlock(minePosition)
        mineBlock.open()

        val sut = Blocks(listOf(normalBlockA, normalBlockB, mineBlock))

        // Act
        val act = sut.isMineOpen()

        // Assert
        assertThat(act).isTrue
    }

    @Test
    @DisplayName("모든 normal block이 열렸는지 확인")
    fun `sut check all opened normal block`() {
        // Arrange
        val normalPositionA = Position.fromApplication(1, 2)
        val normalBlockA = NormalBlock(normalPositionA)
        normalBlockA.open()

        val normalPositionB = Position.fromApplication(4, 2)
        val normalBlockB = NormalBlock(normalPositionB)
        normalBlockB.open()

        val minePosition = Position.fromApplication(10, 10)
        val mineBlock = MineBlock(minePosition)

        val sut = Blocks(listOf(normalBlockA, normalBlockB, mineBlock))

        // Act
        val act = sut.isAllOpenNormalBlock()

        // Assert
        assertThat(act).isTrue
    }

    @Test
    @DisplayName("주어진 position에 있는 block을 찾아준다")
    fun `sut get block when position is given`() {
        // Arrange
        val normalPosition = Position.fromApplication(1, 2)
        val normalBlock = NormalBlock(normalPosition)

        val minePosition = Position.fromApplication(10, 10)
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
        val normalPositionA = Position.fromApplication(1, 2)
        val normalBlockA = NormalBlock(normalPositionA)

        val normalPositionB = Position.fromApplication(4, 2)
        val normalBlockB = NormalBlock(normalPositionB)

        val minePosition = Position.fromApplication(10, 10)
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
        val normalPositionA = Position.fromApplication(1, 2)
        val normalBlockA = NormalBlock(normalPositionA)

        val minePositionA = Position.fromApplication(10, 10)
        val mineBlockA = MineBlock(minePositionA)

        val minePositionB = Position.fromApplication(3, 3)
        val mineBlockB = MineBlock(minePositionB)

        val sut = Blocks(listOf(normalBlockA, mineBlockA, mineBlockB))

        // Act
        val act = sut.findMineBlocks()

        // Assert
        assertThat(act).isEqualTo(listOf(mineBlockA, mineBlockB))
    }
}
