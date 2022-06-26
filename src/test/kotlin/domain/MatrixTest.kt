package domain

import domain.geometric.Dimension
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class MatrixTest : DescribeSpec({

    describe("행렬은") {
        val dimension = Dimension(5, 5)

        it("차원의 넓이만큼 칸을 가진다") {
            val matrix = Matrix.of(dimension, NumberOfMines(1))
            matrix.cells.size shouldBe 25
        }

        context("지뢰 개수가 주어지면") {
            val numberOfMines = NumberOfMines(5)
            val locationSelector = CustomLocationSelector(
                locations(0 to 0, 1 to 1, 2 to 2, 3 to 3, 4 to 4)
            )
            val matrix = Matrix.of(dimension, numberOfMines, locationSelector)
            it("그 수만큼 지뢰 칸을 배치한다") {
                matrix.cells.count { it is Mine } shouldBe numberOfMines.value
            }
        }

        context("전체 칸 수가 차원의 넓이와 같지 않으면") {
            val outerCells = cells(dimension).apply {
                add(Cell.safe(location(15, 15)))
            }
            val cells = listOf(emptyList(), outerCells)
            it("예외를 발생시킨다") {
                cells.forAll {
                    shouldThrowExactly<IllegalArgumentException> {
                        Matrix(dimension, it)
                    }
                }
            }
        }

        context("주어진 차원의 크기를 벗어나는 칸이 포함되면") {
            val invalidCells = cells(dimension).apply {
                set(0, Cell.safe(location(100, 100)))
            }
            it("예외를 발생시킨다") {
                shouldThrowExactly<IllegalArgumentException> {
                    Matrix(dimension, invalidCells)
                }
            }
        }

        context("행렬에 포함된 위치가 주어지면") {
            val cells = cells(dimension).apply {
                replaceToMine(location(0, 0))
                replaceToMine(location(0, 1))
                replaceToMine(location(0, 2))
                replaceToMine(location(1, 0))
                replaceToMine(location(1, 2))
                replaceToMine(location(2, 0))
                replaceToMine(location(2, 1))
                replaceToMine(location(2, 2))
            }
            val matrix = Matrix(dimension, cells)
            it("해당 칸 주변 지뢰의 개수를 구할 수 있다") {
                matrix.countMinesAround(location(1, 1)) shouldBe 8
            }
        }

        context("행렬을 벗어난 위치가 주어지면") {
            val cells = cells(dimension)
            val matrix = Matrix(dimension, cells)
            it("해당 칸 주변 지뢰의 개수를 구할 수 없다") {
                matrix.countMinesAround(location(10, 10)) shouldBe -1
            }
        }
    }
})
