package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

internal class MinesWeeperTest {

    @Test
    internal fun `지뢰의 수는 지뢰찾기 게임의 칸수보다 적어야합니다`() {
        val height = 2
        val width = 2
        val count = 4

        shouldThrow<IllegalArgumentException> {
            MinesWeeper.of(height, width, count)
        }
    }

    @Test
    internal fun `지뢰찾기는 입력한 높이와 너비의 모양으로 생성된다`() {
        val height = 10
        val width = 5
        val count = 3
        val game = MinesWeeper.of(height, width, count)

        game.boards.maxOf { it.location.y } shouldBe height - 1
        game.boards.maxOf { it.location.x } shouldBe width - 1
    }

    @Test
    internal fun `지뢰는 입력한 카운트만큼 생성된다`() {
        val height = 10
        val width = 5
        val count = 3
        val game = MinesWeeper.of(height, width, count)

        game.boards.count { it.isMine } shouldBe count
    }
}
