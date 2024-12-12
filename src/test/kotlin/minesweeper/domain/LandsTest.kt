package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
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

                    lands.openSurroundings(it.point)
                    expectedOpenLands.forAll { it.isOpened() shouldBe true }
                }
            }
        }

        When("모든 땅이 공개 되면") {
            val lands = landsFixture(2)
            lands.openSurroundings(Point(0, 0))
            Then("공개 될 수 있는 땅이 없다.") {
                lands.hasUnopenedLand() shouldBe false
            }
        }

        When("특정 좌표가") {
            val lands = landsFixture(3)
            val land = Point(0, 0)
            val notLand = Point(4, 4)
            Then("땅인지 조회할 수 있다.") {
                lands.find(land) shouldNotBe null
                lands.find(notLand) shouldBe null
            }
        }
    }
})
