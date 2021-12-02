package mine

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineTest {

    @Test
    fun `지뢰의 개수는 음수이면 예외가 발생한다`() {
        val mine = -39

        assertThrows<IllegalArgumentException> { Mine(mine) }
    }
}
