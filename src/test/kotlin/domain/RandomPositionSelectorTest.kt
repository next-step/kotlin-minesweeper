package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RandomPositionSelectorTest {

    @DisplayName("중복되는 좌표가 선택되어서는 안된다.")
    @Test
    fun notDuplicated() {
        val number = height * width - 1
        val positions = selector.selectMinePositions(number, excludedPosition)
        assertThat(positions.distinct().size).isEqualTo(number)
    }

    @DisplayName("Board 위의 좌표 갯수보다 더 많은 좌표들을 선택할 수는 없다.")
    @Test
    fun outOfPool() {
        val number = height * width
        assertThatExceptionOfType(NoSuchElementException::class.java)
            .isThrownBy { selector.selectMinePositions(number, excludedPosition) }
    }

    companion object {
        private const val height = 8
        private const val width = 6
        private val selector = RandomPositionSelector(Board(height, width))
        private val excludedPosition = Position(4, 3)
    }
}
