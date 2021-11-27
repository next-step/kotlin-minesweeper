package util

import domain.Height
import domain.Width
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MinePositionsGeneratorTest {
    @Test
    fun `생성요청한 마인의 개수가 셀의 개수를 초과할 때 에러`() {
        assertThrows<IllegalArgumentException> {
            MinePositionsGenerator.generate(
                Width(0),
                Height(0),
                1
            )
        }
    }

    @Test
    fun `생성요청한 마인의 개수가 셀의 개수를 초과하지 않는다면 마인의 개수만큼 포지션 생성`() {
        val minesPosition = MinePositionsGenerator.generate(
            Width(2),
            Height(2),
            1
        )

        assertEquals(1, minesPosition.size)
    }
}
