package minesweeper.domain.squarestate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmptyTest {
    private lateinit var defaultEmpty: Empty

    @BeforeEach
    fun setUp() {
        defaultEmpty = Empty.default
    }

    @Test
    fun `지뢰 아님`() {
        assertThat(defaultEmpty.isMine).isFalse()
    }

    @Test
    fun `지뢰 개수 업데이트된 Empty가 반환되는지 확인`() {
        // when
        val emptyUpdated: SquareState = defaultEmpty.updateMineCount(3)

        // then
        assertThat(defaultEmpty.mineCount).isEqualTo(0)
        assertThat(emptyUpdated.mineCount).isEqualTo(3)
    }

    @Test
    fun `심볼 확인`() {
        assertThat(defaultEmpty.toString()).isEqualTo("0")
        assertThat(defaultEmpty.updateMineCount(3).toString()).isEqualTo("3")
    }
}
