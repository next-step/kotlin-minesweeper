package mine.sweeper

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.createMineGame
import mine.sweeper.application.MapInitializer
import mine.sweeper.application.MineSweeperGame
import mine.sweeper.domain.Fields
import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class MineSweeperGameTest : StringSpec({
    "지뢰 찾기 게임에서 맵의 상태를 표출할 수 있다." {
        val game = createMineGame()
        shouldNotThrowAny {
            game.fields.sortedList.shouldNotBeNull()
        }
    }

    "지뢰가 맵 크기보다 커서 더이상 지뢰를 심을 수 없다면 IllegalStateException 에외가 발생한다." {
        shouldThrow<IllegalStateException> {
            createMineGame(mineCount = MineCount(9999))
        }
    }

    "필드들에 지뢰들만 남았다면 승리한다." {
        val mapSize = MapSize(Height(2), Width(1))
        val map = MapInitializer(Fields(mapSize)).create(setOf(Position(0, 0)))
        val mineSweeperGame = MineSweeperGame(map)
        mineSweeperGame.select(Position(1, 0))
        mineSweeperGame.status shouldBe GameStatus.COMPLETE
    }

    "지뢰를 열면 게임이 오버된다." {
        val mapSize = MapSize(Height(1), Width(1))
        val map = MapInitializer(Fields(mapSize)).create(setOf(Position(0, 0)))
        val mineSweeperGame = MineSweeperGame(map)
        mineSweeperGame.select(Position(0, 0))
        mineSweeperGame.status shouldBe GameStatus.GAME_OVER
    }
})
