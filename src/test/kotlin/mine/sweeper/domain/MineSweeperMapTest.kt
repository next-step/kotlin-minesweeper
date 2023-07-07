package mine.sweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import mine.sweeper.Fixture

class MineSweeperMapTest : StringSpec({
    val game = Fixture.createMineGame()
    val fields = game.getResult()
    "높이와 너비에 따라 빈 땅을 생성한다" {
        fields.sortedList.size shouldBe 25
    }
})
