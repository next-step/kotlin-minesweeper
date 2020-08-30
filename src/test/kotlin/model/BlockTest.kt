package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlockTest {
    @Test
    @DisplayName("자신 주변의 mineCount 를 sum 해서 반환한다")
    fun `countSurroundingMine`() {
        val minePlate = Square(Number(10), Number(10)).make()
        minePlate.value[0].column.blocks[1].changeToMine()
        minePlate.value[1].column.blocks[1].changeToMine()
        minePlate.value[0].column.blocks[0].setMineCount(0, 0, minePlate)
        assertThat(minePlate.value[0].column.blocks[0].mineCount).isEqualTo(2)
    }
}
