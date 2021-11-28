package mine

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HeightTest {

    @Test
    fun `높이가 0보다 작은 경우`() {
        val height = -1

        assertThrows<IllegalArgumentException> { Height(height) }
    }
}
