package minesweeper.domain

import minesweeper.domain.block.BoardSize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("보드 크기(BoardSize)")
internal class BoardSizeTest {

    @ParameterizedTest(name = "입력 값: {0}, {1}")
    @CsvSource(value = ["1:1", "10:1", "1:10", "10:10"], delimiter = ':')
    fun `가로와 세로 크기로 이루어진다`(widthInt: Int, heightInt: Int) {
        val width = Width(widthInt)
        val height = Height(heightInt)

        val boardSize = BoardSize(width, height)

        assertAll(
            { assertThat(boardSize).isNotNull },
            { assertThat(boardSize).isExactlyInstanceOf(BoardSize::class.java) },
        )
    }

    @ParameterizedTest(name = "입력 값: {0}, {1}, {2}")
    @CsvSource(value = ["1:1:1", "10:1:10", "1:10:10", "10:10:10"], delimiter = ':')
    fun `넓이를 반환할 수 있다`(widthInt: Int, heightInt: Int) {
        val width = Width(widthInt)
        val height = Height(heightInt)

        val boardSize = BoardSize(width, height)
        val area: Int = boardSize.area()

        assertThat(area).isEqualTo(widthInt * heightInt)
    }
}
