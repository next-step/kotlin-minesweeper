package minesweeper.domain.field

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MarkTest {

    @ParameterizedTest
    @CsvSource("MINE, true", "SAFE, false")
    fun `지뢰인지 확인한다`(mark: Mark, expected: Boolean) {
        mark.isMine() shouldBe expected
    }
}
