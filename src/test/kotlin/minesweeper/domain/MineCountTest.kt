package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineCountTest {
    @Test
    fun `주생성자에 음수가 전달될 경우를 테스트`() {
        shouldThrow<IllegalArgumentException> { MineCount(-1) }
    }

    @ParameterizedTest
    @CsvSource(
        "'2', 1, 1,",
        "'-1', 1, 1,",
    )
    fun `부 생성자의 잘못된 파라미터가 전달될 경우를 테스트`(
        count: String,
        width: Int,
        height: Int,
    ) {
        shouldThrow<IllegalArgumentException> { MineCount(count, Width(width), Height(height)) }
    }
}
