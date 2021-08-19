package minesweeper.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineGeneratorTest {

    @Test
    fun `지뢰위치를_생성한다`() {
        val mineGenerator = MineGenerator(Marker(5, 5))
        val positions = mineGenerator.generateMinePositions(RandomPositionGenerator(), 3)

        assertThat(positions.size).isEqualTo(3)
    }

    @ParameterizedTest
    @CsvSource(value = ["4, 5, 30", "5, 5, 25", "1, 2, 10"])
    fun `지뢰의_개수가_땅의_크기보다_큰_경우_예외처리한다`(vertical: Int, height: Int, countOfMine: Int) {
        val mineGenerator = MineGenerator(Marker(vertical, height))

        Assertions.assertThatIllegalArgumentException().isThrownBy {
            mineGenerator.generateMinePositions(RandomPositionGenerator(), countOfMine)
        }
    }
}
