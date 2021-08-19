package minesweeper

import minesweeper.domain.Marker
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
            Marker(height, vertical)
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
}
