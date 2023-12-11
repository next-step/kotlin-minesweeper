package mineswipper.domain.map

import io.kotest.matchers.types.shouldBeInstanceOf
import mineswipper.domain.map.position.Position
import mineswipper.domain.map.position.Positions
import mineswipper.domain.map.position.Size
import mineswipper.domain.map.util.MinePositionStrategy
import org.junit.jupiter.api.Test

class FieldTest {

    @Test
    fun `필드에는 지뢰 발판이 있다`() {
        val size = Size(2, 2)
        val mine = 2
        val field = Field(
            size,
            MineMarkTestStrategy().createMinePosition(size, mine)
        )

        field.findPedal(Position(1, 0)).shouldBeInstanceOf<Mine>()
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
