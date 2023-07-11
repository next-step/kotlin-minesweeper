package minesweeper.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SquareLocationGeneratorTest {
    @Test
    fun `지뢰의 위치를 생성한다`() {
        // given
        val mineLocationGenerator = MineLocationGenerator(ContrivedCoordinateGenerator())
        val height = 10
        val width = 10
        val board: Array<Array<GameBoardSquare>> =
            Array(height) { Array(width) { GameBoardSquare.NumberSquare.createEmpty() } }

        // when
        val listBoard = board.map { it.toList() }
        val generateMineLocation = mineLocationGenerator.generateMineLocation(listBoard)

        // then
        Assertions.assertThat(generateMineLocation).isEqualTo(
            SquareLocation(board.first().size - 1, board.size - 1)
        )
    }
}
