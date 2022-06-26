import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class MineSweeperBoardTest : FunSpec({
    test("높이와 너비, 지뢰 개수를 입력받아서 보드를 구성할 수 있다.") {
        // given
        val height = 4
        val width = 6
        val mineCount = 5

        // when
        val result = MineSweeperBoard.of(height = height, width = width, mineCount = mineCount)

        // then
        result.field.size shouldBe 4
        result.field.forEach {
            it.size shouldBe 6
        }
    }
})
