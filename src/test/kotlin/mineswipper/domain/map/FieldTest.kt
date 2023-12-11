package mineswipper.domain.map

import io.kotest.matchers.shouldBe
import mineswipper.domain.map.position.Position
import mineswipper.domain.map.position.Positions
import mineswipper.domain.map.position.Size
import mineswipper.domain.map.util.MinePositionStrategy
import org.junit.jupiter.api.Test

class FieldTest {

    @Test
    fun `일반 발판은 주변에 지뢰 갯수를 표시한다`() {
        val size = Size(2, 2)
        val mine = 2
        val field = Field(
            size,
            MineMarkTestStrategy().createMinePosition(size, mine)
        )

        val pedal = field.findPedal(Position(0, 0))

        pedal.mark?.value shouldBe "2"
    }
}

class MineMarkTestStrategy : MinePositionStrategy {
    override fun createMinePosition(size: Size, mineAmount: Int): Positions {
        return Positions(
            listOf(
                Position(1, 0),
                Position(0, 1)
            )
        )
    }
}
