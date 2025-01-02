import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class MinesweeperTest {
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
