import io.kotest.assertions.throwables.shouldThrow
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
        result.field.size shouldBe 24
    }

    test("보드를 구성할 때, 안전지대의 개수는 [(높이 X 너비) - 지뢰 개수] 이다.") {
        // given
        val height = 4
        val width = 6
        val mineCount = 5

        // when
        val result = MineSweeperBoard.of(height = height, width = width, mineCount = mineCount)

        // then
        result.field.count { it is SafeZone } shouldBe 19
        result.field.count { it is Mine } shouldBe 5
    }

    test("보드를 구성할 때 높이가 0이하의 값이라면 예외가 발생한다.") {
        // given
        val height = 0
        val width = 6
        val mineCount = 5

        // when then
        shouldThrow<IllegalArgumentException> { MineSweeperBoard.of(height = height, width = width, mineCount = mineCount) }
    }

    test("보드를 구성할 때 너비가 0이하의 값이라면 예외가 발생한다.") {
        // given
        val height = 10
        val width = 0
        val mineCount = 5

        // when then
        shouldThrow<IllegalArgumentException> { MineSweeperBoard.of(height = height, width = width, mineCount = mineCount) }
    }

    test("보드를 구성할 때 지뢰 개수가 0이하의 값이라면 예외가 발생한다.") {
        // given
        val height = 10
        val width = 6
        val mineCount = 0

        // when then
        shouldThrow<IllegalArgumentException> { MineSweeperBoard.of(height = height, width = width, mineCount = mineCount) }
    }
})
