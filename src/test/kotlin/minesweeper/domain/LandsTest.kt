package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import minesweeper.fixture.landsFixture

class LandsTest : BehaviorSpec({
    Given("땅들은") {
        When("땅 리스트를") {
            val rows = listOf(1, 5, 10, 100)
            Then("프로퍼티로 갖는다.") {
                rows.forAll { size ->
                    landsFixture(size).elements.size shouldBe size * size
                }
            }
        }

        When("특정 땅을 인자로 받아") {
            val lands = landsFixture(3)
            Then("주변 땅을 공개할 수 있다.") {
                lands.elements.forEach {
                    val surroundings = Direction.applyTo(it.point)
                    val expectedOpenLands = lands.elements.filter { it.point in surroundings }

                    lands.openSurroundings(it)
                    expectedOpenLands.forAll { it.isOpened() shouldBe true }
                }
            }
        }
    }
})
