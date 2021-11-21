package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PositionSelectorTest {
    @DisplayName("모든 좌표를 선택할 수 있어야 한다.")
    @Test
    fun allPositions() {
        val height = 3
        val width = 2
        val selector = object : PositionSelector(height, width) {
            override fun selectMinePositions(number: Int, excludedPosition: Position): List<Position> = emptyList()
        }
        val expectedPositions = listOf(
            Position(1, 1),
            Position(1, 2),
            Position(2, 1),
            Position(2, 2),
            Position(3, 1),
            Position(3, 2)
        )
        assertThat(selector.allPositions()).isEqualTo(expectedPositions)
    }

    @DisplayName("가운데 점 선택시, 인접한 좌표들을 선택할 수 있어야 한다.")
    @Test
    fun adjacentCenter() {
        val position = Position(3, 3)
        val expectedPositions = listOf(
            Position(2, 2),
            Position(2, 3),
            Position(2, 4),
            Position(3, 2),
            Position(3, 3),
            Position(3, 4),
            Position(4, 2),
            Position(4, 3),
            Position(4, 4)
        )
        assertThat(selector.adjacentPositions(position))
            .isEqualTo(expectedPositions)
    }

    @DisplayName("왼쪽 위 끝점 선택시, 인접한 좌표들을 선택할 수 있어야 한다.")
    @Test
    fun adjacentLeftTop() {
        val position = Position(1, 1)
        val expectedPositions = listOf(
            Position(1, 1),
            Position(1, 2),
            Position(2, 1),
            Position(2, 2)
        )
        assertThat(selector.adjacentPositions(position))
            .isEqualTo(expectedPositions)
    }

    @DisplayName("오른쪽 아래 끝점 선택시, 인접한 좌표들을 선택할 수 있어야 한다.")
    @Test
    fun adjacentRightBottom() {
        val position = Position(height, width)
        val expectedPositions = listOf(
            Position(height - 1, width - 1),
            Position(height - 1, width),
            Position(height, width - 1),
            Position(height, width)
        )
        assertThat(selector.adjacentPositions(position))
            .isEqualTo(expectedPositions)
    }

    companion object {
        private const val height = 5
        private const val width = 6
        private val selector = object : PositionSelector(height, width) {
            override fun selectMinePositions(number: Int, excludedPosition: Position): List<Position> = emptyList()
        }
    }
}
