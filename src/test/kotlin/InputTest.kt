import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class InputTest : StringSpec({
    "width 가 1보다 작을 경우" {
        shouldThrow<IllegalArgumentException> {
            MineSweeper(0, 1, 1)
        }
    }

    "height 가 1보다 작을 경우" {
        shouldThrow<IllegalArgumentException> {
            MineSweeper(1, 0, 1)
        }
    }

    "mine count 가 1보다 작을 경우" {
        shouldThrow<IllegalArgumentException> {
            MineSweeper(1, 1, 0)
        }
    }
})
