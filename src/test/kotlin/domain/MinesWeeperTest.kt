package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import kotlin.IllegalArgumentException

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

        game.boards.size shouldBe 50
        game.boards.maxOf { it.location.x } shouldBe 4
        game.boards.maxOf { it.location.y } shouldBe 9
    }

    @Test
    internal fun `지뢰는 입력한 카운트만큼 생성된다`() {
        val height = 10
        val width = 5
        val count = 3
        val game = MinesWeeper.of(height, width, count)

        game.boards.count { it.cell is Mine } shouldBe count
    }

    @Test
    internal fun `높이와 너비보다 같거나 큰 값을 입력하면 에러가 발생한다`() {
        val height = 10
        val width = 5
        val count = 3
        val game = MinesWeeper.of(height, width, count)

        shouldThrow<IllegalArgumentException> { game.isMine(Location(5, 9)) }
        shouldThrow<IllegalArgumentException> { game.openCell(Location(4, 10)) }
    }

    @Test
    internal fun `주변의 지뢰수만큼 카운트가 올라간다`() {
        val list = listOf(
            Board(Location(0, 0), Mine),
            Board(Location(0, 1), Basic()),
            Board(Location(1, 0), Mine),
            Board(Location(1, 1), Mine)
        )
        val game = MinesWeeper(list)

        game.calculateCount()

        val basic: Basic = game.boards.first { it.location == Location(0, 1) }.cell as Basic
        basic.count shouldBe 3
    }

    @Test
    internal fun `지뢰가 없는 인접한 칸이 모두 열리게 된다`() {
        val list = listOf(
            Board(Location(0, 0), Basic()),
            Board(Location(1, 0), Basic()),
            Board(Location(2, 0), Mine),
            Board(Location(0, 1), Basic()),
            Board(Location(1, 1), Basic()),
            Board(Location(2, 1), Basic())
        )
        val game = MinesWeeper(list)
        val basic1 = game.boards.first { it.location == Location(0, 0) }.cell as Basic
        val basic2 = game.boards.first { it.location == Location(1, 0) }.cell as Basic
        val basic3 = game.boards.first { it.location == Location(0, 1) }.cell as Basic
        val basic4 = game.boards.first { it.location == Location(1, 1) }.cell as Basic
        val basic5 = game.boards.first { it.location == Location(2, 1) }.cell as Basic

        game.calculateCount()
        game.openCell(Location(0, 0))

        basic1.isOpen shouldBe true
        basic2.isOpen shouldBe true
        basic3.isOpen shouldBe true
        basic4.isOpen shouldBe true
        basic5.isOpen shouldBe false
    }

    @Test
    internal fun `지뢰가 없는 칸이 다 열리면 게임은 끝난다`() {
        val list = listOf(
            Board(Location(0, 0), Basic()),
            Board(Location(0, 1), Mine),
            Board(Location(1, 0), Basic()),
            Board(Location(1, 1), Basic())
        )
        val game = MinesWeeper(list)
        val basic1 = game.boards.first { it.location == Location(0, 0) }.cell as Basic
        val basic2 = game.boards.first { it.location == Location(1, 0) }.cell as Basic
        val basic3 = game.boards.first { it.location == Location(1, 1) }.cell as Basic
        basic1.open()
        basic2.open()
        basic3.open()

        game.isSuccess() shouldBe true
    }

    @Test
    internal fun `지뢰있는 칸을 열면 게임은 끝난다`() {
        val list = listOf(
            Board(Location(0, 0), Basic()),
            Board(Location(0, 1), Mine),
            Board(Location(1, 0), Basic()),
            Board(Location(1, 1), Basic())
        )
        val game = MinesWeeper(list)

        game.isMine(Location(0, 1)) shouldBe true
    }
}
