package minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BoardSizeTest {

    @ParameterizedTest
    @CsvSource("0, 1", "1, 0", "0, 0")
    fun `게임판의 가로 세로는 0보다 커야한다`(width: Int, height: Int) {
        shouldThrow<IllegalArgumentException> {
            BoardSize(width, height)
        }
    }
}
