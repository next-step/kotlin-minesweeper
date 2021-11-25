package domain

import exception.IllegalPositionException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BoardTest {
    lateinit var board: Board

    @BeforeEach
    fun setUp() {
        board = Board(HEIGHT, WIDTH)
    }

    @DisplayName("board 의 height 와 width 정보를 알 수 있어야 한다.")
    @Test
    fun heightWidth() {
        assertAll(
            { assertThat(board.height()).isEqualTo(HEIGHT) },
            { assertThat(board.width()).isEqualTo(WIDTH) }
        )
    }

    @DisplayName("cell 들이 open 되었는지 여부를 알 수 있어야 한다.")
    @Test
    fun isAllOpen() {
        val positions = listOf(position, Position(HEIGHT, WIDTH))
        assertThat(getCell(positions[0]).isOpen()).isFalse
        assertThat(getCell(positions[1]).isOpen()).isFalse
        assertThat(board.isAllOpen()).isFalse
        getCell(positions[0]).open()
        assertThat(getCell(positions[0]).isOpen()).isTrue
        assertThat(getCell(positions[1]).isOpen()).isFalse
        assertThat(board.isAllOpen()).isFalse
        getCell(positions[1]).open()
        assertThat(getCell(positions[0]).isOpen()).isTrue
        assertThat(getCell(positions[1]).isOpen()).isTrue
        assertThat(board.isAllOpen()).isTrue
    }

    @DisplayName("board 의 범위를 벗어난 좌표에는 접근할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    fun illegalPosition(number: Int) {
        val position = Position(HEIGHT + number, WIDTH + number)
        assertThatExceptionOfType(IllegalPositionException::class.java)
            .isThrownBy { getCell(position) }
    }

    private fun getCell(position: Position): Cell = board.getCell(position)

    companion object {
        private const val HEIGHT = 1
        private const val WIDTH = 2
        private val position = Position()
    }
}
