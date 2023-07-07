package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture.createMineGame
import mine.sweeper.domain.value.MineCount

class VultureTest : StringSpec({

    "벌처가 지뢰를 선언 개수만큼 설치한다." {
        listOf(
            MineCount(5),
            MineCount(3),
            MineCount(1),
            MineCount(10),
        ).forAll { input ->
            val game = createMineGame(mineCount = input)
            val result = game.getResult()
            val count = result.sortedList.count { it is MineField }

            count shouldBe input.value
        }
    }
})
