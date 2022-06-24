package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import minesweeper.domain.Coordinates
import minesweeper.domain.DefaultCellsGenerator
import minesweeper.domain.MineCount
import minesweeper.domain.MineSpawner

class BoardGeneratorTest : DescribeSpec({

    describe("generate") {
        context("높이와 너비가 주어지면") {
            it("해당 크기의 Cell 들을 생성한다.") {
                val cells = DefaultCellsGenerator().generate(Area(2, 2), MineCount(1))
                cells.size shouldBe 4
            }
        }

        context("지뢰의 개수가 만큼") {
            it("지뢰를 추가한다.") {
                val cells = DefaultCellsGenerator().generate(Area(2, 2), MineCount(2))
                cells.filterIsInstance<Cell.Mine>().size shouldBe 2
            }
        }

        context("지정된 위치에") {
            it("지뢰를 배치한다.") {
                val mineSpawner = TestMineSpawner(listOf(Coordinate(0, 0), Coordinate(1, 1)))
                val cells = DefaultCellsGenerator(mineSpawner).generate(Area(2, 2), MineCount(2))

                cells shouldContainAll listOf(
                    Cell.Mine(Coordinate(0, 0)),
                    Cell.Block(Coordinate(0, 1), 2),
                    Cell.Block(Coordinate(1, 0), 2),
                    Cell.Mine(Coordinate(1, 1))
                )
            }
        }

        context("1칸 거리에 지뢰가 두개가 있다면") {
            it("지뢰 개수는 2를 가진다.") {
                val mineSpawner = TestMineSpawner(listOf(Coordinate(0, 0), Coordinate(1, 1)))
                val cells = DefaultCellsGenerator(mineSpawner).generate(Area(2, 2), MineCount(2))

                cells shouldContain Cell.Block(Coordinate(0, 1), 2)
            }
        }
    }
})

class TestMineSpawner(private val coordinates: List<Coordinate>) : MineSpawner {
    override fun spawn(area: Area, count: MineCount): Coordinates {
        return Coordinates(coordinates.toSet())
    }
}
