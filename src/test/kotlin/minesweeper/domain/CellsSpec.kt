package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CellsSpec : DescribeSpec(
    {
        describe("더하기 연산 검증") {
            context("두 셀 컬렉션을 더하면") {
                val cellA = Mine()
                val cellB = Normal()
                val cellsA = Cells(listOf(cellA))
                val cellsB = Cells(listOf(cellB))

                val cells = cellsA + cellsB

                it("병합된 셀 컬렉션을 반환한다.") {
                    cells shouldBe Cells(listOf(cellA, cellB))
                }
            }
        }

        describe("셔플 연산 검증") {
            context("셀 컬렉션을 셔플하면") {
                val cellA = Mine()
                val cellB = Normal()
                val cellC = Normal()
                val cells = Cells(listOf(cellA, cellB, cellC))

                val shuffledCells = cells.shuffled()

                it("셔플된 셀 컬렉션을 반환한다.") {
                    shuffledCells.size shouldBe 3
                    shuffledCells.any { it == cellA } shouldBe true
                    shuffledCells.any { it == cellB } shouldBe true
                    shuffledCells.any { it == cellC } shouldBe true
                }
            }
        }

        describe("청크 연산 검증") {
            context("셀 컬렉션(개수:9) 을 청크(width:3)하면") {
                val cells = Cells(List(9) { Normal() })

                val chunkedCells = cells.chunked(3)

                it("청크된 셀 컬렉션을 반환한다.") {
                    chunkedCells.size shouldBe 3
                    chunkedCells[0].size shouldBe 3
                    chunkedCells[1].size shouldBe 3
                    chunkedCells[2].size shouldBe 3
                }
            }
        }

        describe("빈 Cells 생성 검증") {
            it("빈 Cells 를 생성한다.") {
                val cells = Cells.normal(9)

                cells.values.size shouldBe 9
                cells.values.all { it is Normal } shouldBe true
            }
        }

        describe("지뢰 Cells 생성 검증") {
            it("지뢰 Cells 를 생성한다.") {
                val cells = Cells.mine(9)

                cells.values.size shouldBe 9
                cells.values.all { it is Mine } shouldBe true
            }
        }
    },
)
