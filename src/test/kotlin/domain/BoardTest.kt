package domain

import exception.IllegalPositionException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class BoardTest {
    lateinit var board: Board

    @BeforeEach
    fun setUp() {
        board = Board(height, width)
    }

    @DisplayName("board 의 height 와 width 정보를 알 수 있어야 한다.")
    @Test
    fun heightWidth() {
        assertAll(
            { assertThat(board.height()).isEqualTo(height) },
            { assertThat(board.width()).isEqualTo(width) }
        )
    }

    @DisplayName("cell 들이 open 되었는지 여부를 알 수 있어야 한다.")
    @Test
    fun isAllOpen() {
        val positions = listOf(position, Position(height, width))
        assertThat(board.isOpen(positions[0])).isFalse
        assertThat(board.isOpen(positions[1])).isFalse
        assertThat(board.isAllOpen()).isFalse
        board.open(positions[0])
        assertThat(board.isOpen(positions[0])).isTrue
        assertThat(board.isOpen(positions[1])).isFalse
        assertThat(board.isAllOpen()).isFalse
        board.open(positions[1])
        assertThat(board.isOpen(positions[0])).isTrue
        assertThat(board.isOpen(positions[1])).isTrue
        assertThat(board.isAllOpen()).isTrue
    }

    @DisplayName("특정 좌표에 지뢰를 설치할 수 있어야 한다.")
    @Test
    fun mine() {
        assertThat(board.isMine(position)).isFalse
        board.layMine(position)
        assertThat(board.isMine(position)).isTrue
    }

    @DisplayName("특정 좌표의 mineNumber 를 증가시키면, 더이상 blank 좌표가 아니게 된다.")
    @Test
    fun isBlank() {
        assertThat(board.isBlank(position)).isTrue
        board.increaseMineNumber(position)
        assertThat(board.isMine(position)).isFalse
    }

    @DisplayName("board 의 범위를 벗어난 좌표에는 접근할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    fun illegalPosition(number: Int) {
        val position = Position(height + number, width + number)
        val assertions = listOf(
            { board.isOpen(position) },
            { board.isMine(position) },
            { board.isBlank(position) },
            { board.open(position) },
            { board.layMine(position) },
            { board.increaseMineNumber(position) }
        ).map {
            Executable {
                assertThatExceptionOfType(IllegalPositionException::class.java)
                    .isThrownBy { it() }
            }
        }
        assertAll(assertions)
    }

    companion object {
        private const val height = 1
        private const val width = 2
        private val position = Position()
    }
}
