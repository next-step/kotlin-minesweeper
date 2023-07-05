package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.Companion.MINE_SWEEPER_GAME
import mine.sweeper.application.value.MineCount

class VultureTest : StringSpec({

    "벌처가 지뢰를 선언 개수만큼 설치한다." {
        listOf(
            MineCount(5),
            MineCount(3),
            MineCount(1),
            MineCount(10),
        ).forAll { input ->
            val game = MINE_SWEEPER_GAME()
            game.setMine(input)
            val result = game.getResult()

            val count = result.fields.count { it is MineField }

            count shouldBe input.value
        }
    }
})
