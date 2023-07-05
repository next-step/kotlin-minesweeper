package minesweeper.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineLocationGeneratorTest {

    private lateinit var mineLocationGenerator: MineLocationGenerator

    @BeforeEach
    fun setUp() {
        mineLocationGenerator = MineLocationGenerator(ContrivedCoordinateGenerator())
    }

    @Test
    fun `지뢰의 위치를 생성한다`() {
        val height = 10
        val width = 10
        val board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }

        // when
        val listBoard = board.map { it.toList() }
        val generateMineLocation = mineLocationGenerator.generateMineLocation(listBoard)

        // then
        org.assertj.core.api.Assertions.assertThat(generateMineLocation).isEqualTo(
            MineLocation(board.first().size - 1, board.size - 1)
        )
    }
}
