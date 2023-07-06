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
    internal fun `지뢰찾기는 입력한 높이와 너비의 크기로 생성된다`() {
        val height = 10
        val width = 5
        val count = 3
        val game = MinesWeeper.of(height, width, count)

        game.boards.keys.size shouldBe 50
    }

    @Test
    internal fun `지뢰는 입력한 카운트만큼 생성된다`() {
        val height = 10
        val width = 5
        val count = 3
        val game = MinesWeeper.of(height, width, count)

        game.boards.values.count { it is Mine } shouldBe count
    }

    @Test
    internal fun `주변의 지뢰수만큼 카운트가 올라간다`() {
        val map = mapOf(
            Location(0, 0) to Mine,
            Location(0, 1) to Basic(),
            Location(1, 0) to Mine,
            Location(1, 1) to Mine
        )
        val game = MinesWeeper(map)
        game.calculateCount()

        val basic: Basic = game.boards.getOrDefault(Location(0, 1)) { Basic() } as Basic
        basic.count shouldBe 3
    }
}
