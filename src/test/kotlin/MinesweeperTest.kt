import domain.Mine
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MinesweeperTest {

    @Test
    fun main() {
        Mine().mark shouldBe "*"
    }
}
