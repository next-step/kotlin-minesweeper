package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MarkerTest {

    @ParameterizedTest
    @CsvSource(value = ["-10, 2", "0, 5", "2, 0", "5, -10"])
    fun `높이 및 너비가 자연수가 아닌경우 예외처리된다`(height: Int, vertical: Int) {
        assertThatIllegalArgumentException().isThrownBy {
            Marker(vertical, height)
        }
    }

    @Test
    fun `지뢰찾기를_위한_땅을_만든다`() {
        val marker = Marker(2, 2)

        val ground = marker.generateAllGround()

        assertThat(ground).containsExactly(
            listOf("C ", "C "),
            listOf("C ", "C ")
        )
    }

    @Test
    fun `지뢰위치를_생성한다`() {
        val marker = Marker(5, 3)

        val position = marker.generateMinePosition(MockPositionGenerator())

        assertThat(position).isEqualTo(Position(5, 3))
    }

    @Test
    fun `땅의_전체크기를_전달한다`() {
        val marker = Marker(4, 5)

        assertThat(marker.size()).isEqualTo(20)
    }
}
