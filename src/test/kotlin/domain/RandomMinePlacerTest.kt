package domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RandomMinePlacerTest : FunSpec({
    val placer = RandomMinePlacer()
    context("placeMines") {
        test("지뢰 개수가 정상 범위일 때 지뢰를 정상적으로 설치한다") {
            // given
            val cells = Cells.create(3, 3) // 9개 셀
            val mineCount = 3

            // when
            val updatedCells = placer.placeMines(cells, mineCount)

            // then
            val minedCellsCount = updatedCells.allCells().count { it.hasMine }
            minedCellsCount shouldBe mineCount
        }

        test("지뢰 개수가 0이면 아무 셀에도 지뢰가 설치되지 않는다") {
            // given
            val cells = Cells.create(2, 2) // 4개 셀
            val mineCount = 0

            // when
            val updatedCells = placer.placeMines(cells, mineCount)

            // then
            val minedCellsCount = updatedCells.allCells().count { it.hasMine }
            minedCellsCount shouldBe 0
        }

        test("지뢰 개수가 설치 가능한 셀 수와 같으면 모든 셀에 지뢰가 설치된다") {
            // given
            val cells = Cells.create(2, 2) // 4개 셀
            val mineCount = 4

            // when
            val updatedCells = placer.placeMines(cells, mineCount)

            // then
            val minedCellsCount = updatedCells.allCells().count { it.hasMine }
            minedCellsCount shouldBe mineCount
        }

        test("지뢰 개수가 설치 가능한 셀 수보다 많으면 예외가 발생한다") {
            // given
            val cells = Cells.create(2, 2) // 4개 셀
            val mineCount = 5

            // when, then
            shouldThrowExactly<IllegalArgumentException> {
                placer.placeMines(cells, mineCount)
            }
        }
    }
})
