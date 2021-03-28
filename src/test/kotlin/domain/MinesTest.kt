package domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MinesTest {

    @Test
    fun `지뢰가 위치하는지 확인한다`() {
        val mine1 = Mine(Position(1, 2))
        val mine2 = Mine(Position(0, 3))

        val mines = Mines(listOf(mine1, mine2))
        Assertions.assertThat(mines.isMine(1, 2)).isTrue()
        Assertions.assertThat(mines.isMine(0, 3)).isTrue()
        Assertions.assertThat(mines.isMine(0, 0)).isFalse()
    }
}
