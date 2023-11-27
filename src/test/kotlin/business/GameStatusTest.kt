package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameStatusTest {

    @Test
    fun `continue인지 확인한다`() {
        // given
        val gameStatus = GameStatus.CONTINUE

        // when
        val isContinue = gameStatus.isContinue()

        // then
        isContinue shouldBe true
    }

    @Test
    fun `continue가 아닌지 확인한다`() {
        // given
        val gameStatus = GameStatus.WIN

        // when
        val isContinue = gameStatus.isContinue()

        // then
        isContinue shouldBe false
    }
}
