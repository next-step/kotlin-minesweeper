package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlockGeneratorTest {

    @Test
    fun `블록을 생성하여 리턴한다`() {
        val givenWidth = Width(10)
        val givenHeight = Height(10)
        val givenArea = Area(givenWidth, givenHeight)

        val generateBlocks = BlockGenerator.generateBlocks(givenArea, mineCount = 3)

        assertThat(generateBlocks).isNotNull
    }
}
