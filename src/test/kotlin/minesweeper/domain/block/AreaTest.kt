package minesweeper.domain.block

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AreaTest {

    @Test
    fun `넓이를 리턴한다`() {
        val givenWidth = Width(10)
        val givenHeight = Height(10)
        val givenArea = Area(givenWidth, givenHeight)

        val actual = givenArea.getArea()

        assertThat(actual).isEqualTo(100)
    }
}
