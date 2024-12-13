package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LocationTest {
    @ParameterizedTest
    @CsvSource(
        "-1, 0",
        "0, -1",
        "-1, -1",
    )
    fun `주 생성자에 잘못된 범위가 전달되었을 경우를 테스트`(
        x: Int,
        y: Int,
    ) {
        shouldThrow<IllegalArgumentException> { Location(x, y) }
    }
}
