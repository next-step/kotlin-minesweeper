package mine

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineTest {

    @Test
    fun `지뢰의 개수는 음수이면 안된다`() {
        val mine = -39

        assertThrows<IllegalArgumentException> { Mine(mine) }
    }
}
