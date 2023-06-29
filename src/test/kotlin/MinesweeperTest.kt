import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class MinesweeperTest {
    @Test
    internal fun `지뢰판의 높이는 0보다 커야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Minesweeper(size = Size(height = 0, width = 1), mineCount = 1)
        }
    }

    @Test
    internal fun `지뢰판의 너비는 0보다 커야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Minesweeper(size = Size(height = 1, width = 0), mineCount = 1)
        }
    }

    @Test
    internal fun `지뢰판의 높이는 100보다 작아야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Minesweeper(size = Size(height = 101, width = 1), mineCount = 1)
        }
    }

    @Test
    internal fun `지뢰판의 너비는 100보다 작아야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Minesweeper(size = Size(height = 1, width = 101), mineCount = 1)
        }
    }

    @Test
    internal fun `지뢰의 개수는 높이와 너비의 곱 보다 클 수 없다`() {
        shouldThrow<IllegalArgumentException> {
            Minesweeper(size = Size(height = 1, width = 1), mineCount = 2)
        }
    }

    @Test
    internal fun `지뢰의 개수는 최소 1개 이상이어야 한다`() {
        shouldThrow<IllegalArgumentException> {
            Minesweeper(size = Size(height = 1, width = 1), mineCount = 0)
        }
    }
}
