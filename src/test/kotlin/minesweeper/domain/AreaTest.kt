package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AreaTest {

    @Test
    fun `넓이 정보를 생성할 수 있다`() {
        val givenWidth = Width(10)
        val givenHeight = Height(10)

        assertThat(Area(givenWidth, givenHeight)).isNotNull
    }
}
