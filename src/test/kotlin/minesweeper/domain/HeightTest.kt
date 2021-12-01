package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo

@DisplayName("높이(Height)")
internal class HeightTest {

    @RepeatedTest(value = 10, name = "현재, {currentRepetition}/{totalRepetitions}")
    fun `1이상의 숫자로 이루어진다`(repetitionInfo: RepetitionInfo) {
        val height = Height(repetitionInfo.currentRepetition)

        assertAll(
            { assertThat(height).isNotNull },
            { assertThat(height).isExactlyInstanceOf(height) },
        )
    }
}
