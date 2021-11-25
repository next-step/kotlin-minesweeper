package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RandomPositionSelectorTest {

    @DisplayName("중복된 좌표가 선택되어서는 안된다.")
    @Test
    fun notDuplicated() {
        val mineNumber = MineNumber(HEIGHT * WIDTH - 1)
        val positions = selector.selectMinePositions(mineNumber, excludedPosition)
        assertThat(positions).doesNotHaveDuplicates()
    }

    @DisplayName("배제한 좌표가 선택되어서는 안된다.")
    @Test
    fun noExcluded() {
        val mineNumber = MineNumber(HEIGHT * WIDTH - 1)
        val positions = selector.selectMinePositions(mineNumber, excludedPosition)
        assertThat(positions).doesNotContain(excludedPosition)
    }

    @DisplayName("Board 위의 좌표 갯수보다 더 많은 좌표들을 선택할 수는 없다.")
    @Test
    fun outOfPool() {
        val mineNumber = MineNumber(HEIGHT * WIDTH)
        assertThatExceptionOfType(NoSuchElementException::class.java)
            .isThrownBy { selector.selectMinePositions(mineNumber, excludedPosition) }
    }

    companion object {
        private const val HEIGHT = 8
        private const val WIDTH = 6
        private val selector = RandomPositionSelector(Board(HEIGHT, WIDTH))
        private val excludedPosition = Position(4, 3)
    }
}
