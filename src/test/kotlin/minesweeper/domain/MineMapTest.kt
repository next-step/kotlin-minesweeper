package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

fun MineSweeperMap(height: Int, width: Int): MineSweeperMap {
    return MineSweeperMap(Height(height), Width(width))
}
class MineMapTest : BehaviorSpec({

    given("높이 5, 너비 5인 지뢰판이 주어진다면") {
        val mineSweeperMap = MineSweeperMap(5, 5)
        When("지뢰판 좌표를 생성할 시") {
            val result = mineSweeperMap.createPosition()
            Then("좌표의 개수는 25개이다") {
                result.mineSweeperIndexes.count() shouldBe 25
            }
        }
    }
})
