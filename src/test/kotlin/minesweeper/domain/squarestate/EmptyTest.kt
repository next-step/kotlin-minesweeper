package minesweeper.domain.squarestate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmptyTest {
    private lateinit var empty: Empty

    @BeforeEach
    fun setUp() {
        empty = Empty()
    }

    @Test
    fun `지뢰 아님`() {
        assertThat(empty.isMine).isFalse()
    }

    @Test
    fun `지뢰 개수 업데이트된 Empty가 반환되는지 확인`() {
        // when
        val emptyUpdated: SquareState = empty.updateMineCount(3)

        // then
        assertThat(empty.mineCount).isEqualTo(0)
        assertThat(emptyUpdated.mineCount).isEqualTo(3)
    }

    @Test
    fun `심볼 확인`() {
        assertThat(empty.toString()).isEqualTo("0")
        assertThat(Empty(3).toString()).isEqualTo("3")
    }
}
