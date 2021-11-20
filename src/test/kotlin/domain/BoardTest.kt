package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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

    @DisplayName("모든 cell 들을 open 하였는지 여부를 알 수 있어야 한다.")
    @Test
    fun isAllOpen() {
        assertThat(board.isAllOpen()).isFalse
        board.open(Position(1, 1))
        assertThat(board.isAllOpen()).isFalse
        board.open(Position(1, 2))
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

    companion object {
        private const val height = 1
        private const val width = 2
        private val position = Position(1, 1)
    }
}
