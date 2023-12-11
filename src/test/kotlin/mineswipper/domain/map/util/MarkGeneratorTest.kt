package mineswipper.domain.map.util

import io.kotest.matchers.shouldBe
import mineswipper.domain.map.Field
import mineswipper.domain.map.MineMarkTestStrategy
import mineswipper.domain.map.position.Position
import mineswipper.domain.map.position.Size
import org.junit.jupiter.api.Test

class MarkGeneratorTest {
    @Test
    fun `일반 발판은 주변에 지뢰 갯수를 표시한다`() {
        val size = Size(2, 2)
        val mine = 2
        val field = Field(
            size,
            MineMarkTestStrategy().createMinePosition(size, mine)
        )

        MarkGenerator.markGeneration(field)

        val pedal = field.findPedal(Position(0, 0))
        pedal.mark?.value shouldBe "2"
    }
}
