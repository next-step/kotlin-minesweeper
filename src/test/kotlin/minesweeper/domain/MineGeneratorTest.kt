package minesweeper.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineGeneratorTest {

    @Test
    fun `지뢰위치를_생성한다`() {
        val mineGenerator = MineGenerator(Marker(5, 5))
        val positions = mineGenerator.generateMinePositions(RandomPositionGenerator(), 3)

        assertThat(positions.size).isEqualTo(3)
    }

    @Test
    fun `지뢰의_개수가_땅의_크기보다_큰_경우_예외처리한다`() {
        val mineGenerator = MineGenerator(Marker(4, 5))

        Assertions.assertThatIllegalArgumentException().isThrownBy {
            mineGenerator.generateMinePositions(RandomPositionGenerator(), 30)
        }
    }
}
