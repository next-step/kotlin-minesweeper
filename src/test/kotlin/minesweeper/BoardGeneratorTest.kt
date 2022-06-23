package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.BoardGenerator
import minesweeper.domain.Area
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import minesweeper.domain.Coordinates
import minesweeper.domain.MineCount
import minesweeper.domain.MineSpawner

class BoardGeneratorTest : DescribeSpec({

    describe("generate") {
        context("높이와 너비가 주어지면") {
            it("해당 크기의 보드를 생성한다.") {
                val board = BoardGenerator().generate(Area(2, 2), MineCount(1))
                board.cells.size shouldBe 4
            }
        }

        context("크기보다 지뢰의 개수가 많으면") {
            it("Invalid 상태를 반환한다.") {
                shouldThrow<IllegalArgumentException> {
                    BoardGenerator().generate(Area(1, 1), MineCount(10))
                }
            }
        }

        context("지뢰의 개수가 만큼") {
            it("지뢰를 보드에 추가한다.") {
                val board = BoardGenerator().generate(Area(2, 2), MineCount(2))
                board.remainMineCount() shouldBe 2
            }
        }

        context("지정된 위치에") {
            it("지뢰를 배치한다.") {
                val mineSpawner = TestMineSpawner(listOf(Coordinate(0, 0), Coordinate(1, 1)))
                val board = BoardGenerator(mineSpawner).generate(Area(2, 2), MineCount(2))

                board.cells shouldContainAll listOf(
                    Cell.Mine(Coordinate(0, 0)),
                    Cell.None(Coordinate(0, 1), 2),
                    Cell.None(Coordinate(1, 0), 2),
                    Cell.Mine(Coordinate(1, 1))
                )
            }
        }

        context("1칸 거리에 지뢰가 두개가 있다면") {
            it("지뢰 개수는 2를 가진다.") {
                val mineSpawner = TestMineSpawner(listOf(Coordinate(0, 0), Coordinate(1, 1)))
                val board = BoardGenerator(mineSpawner).generate(Area(2, 2), MineCount(2))

                board.cells shouldContain Cell.None(Coordinate(0, 1), 2)
            }
        }
    }
})

class TestMineSpawner(private val coordinates: List<Coordinate>) : MineSpawner {
    override fun spawn(area: Area, count: MineCount): Coordinates {
        return Coordinates(coordinates.toSet())
    }
}
