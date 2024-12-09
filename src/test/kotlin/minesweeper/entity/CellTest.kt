package minesweeper.entity

import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CellTest : BehaviorSpec({
    Given("Cell 객체를 생성할 때") {

        When("Mine 타입의 Cell을 생성하면") {
            Then("해당 Cell은 Mine 타입이어야 한다") {
                val coordinate = Coordinate(2, 3)
                val mineCell = Cell.Mine(coordinate)

                mineCell.shouldBeInstanceOf<Cell.Mine>()
                mineCell.coordinate shouldBe coordinate
            }
        }

        When("Empty 타입의 Cell을 생성하면") {
            Then("해당 Cell은 Empty 타입이어야 한다") {
                val coordinate = Coordinate(5, 6)
                val emptyCell = Cell.Empty(coordinate)

                emptyCell.shouldBeInstanceOf<Cell.Empty>()
                emptyCell.coordinate shouldBe coordinate
            }
        }
    }

    Given("Cell 객체의 동등성을 비교할 때") {

        When("같은 좌표의 Mine 객체를 비교하면") {
            Then("두 Mine 객체는 같아야 한다") {
                val coordinate = Coordinate(1, 1)
                val mine1 = Cell.Mine(coordinate)
                val mine2 = Cell.Mine(coordinate)

                mine1 shouldBe mine2
            }
        }

        When("같은 좌표의 Empty 객체를 비교하면") {
            Then("두 Empty 객체는 같아야 한다") {
                val coordinate = Coordinate(4, 7)
                val empty1 = Cell.Empty(coordinate)
                val empty2 = Cell.Empty(coordinate)

                empty1 shouldBe empty2
            }
        }

        When("다른 타입의 Cell 객체를 비교하면") {
            Then("두 객체는 다르다고 간주된다") {
                val coordinate = Coordinate(3, 3)
                val mine = Cell.Mine(coordinate)
                val empty = Cell.Empty(coordinate)

                mine shouldNotBe empty
            }
        }

        When("좌표가 다른 같은 타입의 Cell 객체를 비교하면") {
            Then("두 객체는 다르다고 간주된다") {
                val mine1 = Cell.Mine(Coordinate(1, 1))
                val mine2 = Cell.Mine(Coordinate(2, 2))

                mine1 shouldNotBe mine2
            }
        }
    }
})
