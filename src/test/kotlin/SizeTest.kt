import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class SizeTest {
    @Test
    internal fun `지뢰판의 높이는 0보다 커야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Size(height = 0, width = 1)
        }
    }

    @Test
    internal fun `지뢰판의 너비는 0보다 커야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Size(height = 1, width = 0)
        }
    }

    @Test
    internal fun `지뢰판의 높이는 100보다 작아야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Size(height = 101, width = 1)
        }
    }

    @Test
    internal fun `지뢰판의 너비는 100보다 작아야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Size(height = 1, width = 101)
        }
    }
}
