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

    @Test
    @DisplayName("position에 해당하는 block을 가져온다")
    fun `sut get block by position`() {
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
        val position = Position.fromApplication(3, 3)
        val sut = GameMap.from(gameSize, minePositions)

        // Act
        val actual = sut.getBlockByPosition(position)

        // Assert
        assertThat(actual.position).isEqualTo(position)
    }

    @Test
    @DisplayName("이미 오픈된 블럭인지 체크한다")
    fun `sut check opened block`() {
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
        val position = Position.fromApplication(3, 3)
        val sut = GameMap.from(gameSize, minePositions)
        sut.open(position)

        // Act
        val actual = sut.checkAlreadyOpened(position)

        // Assert
        assertThat(actual).isTrue
    }

    @Test
    @DisplayName("normal block을 열었을때 0이면 주변의 block도 연다")
    fun `sut open surround blocks when opened block's marker is 0`() {
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
        val position = Position.fromApplication(5, 5)
        val sut = GameMap.from(gameSize, minePositions)

        // Act
        sut.open(position)

        // Assert
        val nearByOpened = sut.getBlockByPosition(Position.fromApplication(5, 6)).isOpened
        assertThat(nearByOpened).isTrue
    }

    @Test
    @DisplayName("지뢰를 오픈하면 게임에서 패배한다")
    fun `sut evaluateGameResult should return LOSE when mine block is opened`() {
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

        val sut = GameMap.from(gameSize = gameSize, minePositions = minePositions)
        sut.open(minePositions.values.first())

        // Act
        val actual = sut.evaluateGameResult()

        // Assert
        assertThat(actual).isEqualTo(OpenResult.LOSE)
    }

    @Test
    @DisplayName("지뢰를 제외한 일반 블록을 전부 오픈하면 게임에서 승리한다")
    fun `sut evaluateGameResult should return WIN when all normal blocks are opened`() {
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
        val sut = GameMap.from(gameSize = gameSize, minePositions = minePositions)
        sut.blocks.values.filterIsInstance<NormalBlock>().forEach { it.open() }

        // Act
        val actual = sut.evaluateGameResult()

        // Assert
        assertThat(actual).isEqualTo(OpenResult.WIN)
    }

    @Test
    @DisplayName("지뢰를 오픈하지 않고, 일반 블록을 전부 오픈하지 않았다면 게임을 계속한다")
    fun `evaluateGameResult should return CONTINUE when the game is not over`() {
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
        val sut = GameMap.from(gameSize = gameSize, minePositions = minePositions)

        // Act
        val actual = sut.evaluateGameResult()

        // Assert
        assertThat(actual).isEqualTo(OpenResult.CONTINUE)
    }
}
