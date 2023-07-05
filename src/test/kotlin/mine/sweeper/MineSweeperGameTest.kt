package mine.sweeper

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.Companion.MINE_SWEEPER_GAME
import mine.sweeper.application.value.GameStatus
import mine.sweeper.application.value.Height
import mine.sweeper.application.value.MineCount
import mine.sweeper.application.value.Width
import mine.sweeper.view.dto.Position

class MineSweeperGameTest : StringSpec({
    "지뢰 찾기 게임에서 맵의 상태를 표출할 수 있다." {
        val game = MINE_SWEEPER_GAME()
        game.setMine(MineCount(3))

        shouldNotThrowAny {
            game.getResult()
        }
    }

    "지뢰가 맵 크기보다 커서 더이상 지뢰를 심을 수 없다면 IllegalStateException 에외가 발생한다." {
        val game = MINE_SWEEPER_GAME()
        shouldThrow<IllegalStateException> {
            game.setMine(MineCount(9999))
        }
    }

    "필드들에 지뢰들만 남았다면 승리한다." {
        val game = MINE_SWEEPER_GAME(Height(3), Width(3))
        val result = game.open(Position(1, 1))
        result shouldBe GameStatus.COMPLETE
    }
})
