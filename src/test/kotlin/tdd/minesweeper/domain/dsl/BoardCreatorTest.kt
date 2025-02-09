package tdd.minesweeper.domain.dsl

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.AdjacentMines
import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.ClosedCell
import tdd.minesweeper.domain.Location

class BoardCreatorTest : BehaviorSpec({

    given("높이, 너비, 지뢰 개수를 받아") {
        val height = 5
        val width = 5
        val countOfMines = 5

        `when`("보드를 만들면") {
            val result =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                }

            then("25개의 닫힌 셀을 만들 수 있다") {
                result.area shouldBe Area(height = height, width = width)
                result.cells.size shouldBe 25
                result.cells.all { it is ClosedCell } shouldBe true
            }

            then("25개의 셀 중 5개는 지뢰를 가지고 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
            }
        }
    }

    given("높이를") {
        val width = 5
        val countOfMines = 5

        `when`("입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    board {
                        width(width)
                        countOfMines(countOfMines)
                    }
                }
            }
        }
        `when`("양수로 입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    board {
                        width(width)
                        height(0)
                        countOfMines(countOfMines)
                    }
                }
            }
        }
    }

    given("너비를 입력하지 않으면") {
        val height = 5
        val countOfMines = 5

        `when`("입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    board {
                        height(height)
                        countOfMines(countOfMines)
                    }
                }
            }
        }
        `when`("양수로 입력하지 않으면") {
            then("보드를 생성할 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    board {
                        height(height)
                        width(0)
                        countOfMines(countOfMines)
                    }
                }
            }
        }
    }

    given("보드의 전체 셀 수가 5x5 = 25개일 때") {
        val height = 5
        val width = 5
        val countOfMines = 26

        `when`("지뢰를 보드의 전체 셀 수보다 더 많이") {
            then("만들 수 없다") {
                shouldThrow<IllegalArgumentException> {
                    board {
                        height(height)
                        width(width)
                        countOfMines(countOfMines)
                    }
                }
            }
        }
    }

    given("높이 5, 너비 5, 지뢰 개수 5개를 받아") {
        val height = 5
        val width = 5
        val countOfMines = 5

        `when`("5개의 서로 다른 지뢰위치를 수동으로 받아 보드를 만들면") {
            val result =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                    mineAt(1, 4)
                    mineAt(1, 5)
                    mineAt(2, 1)
                    mineAt(4, 3)
                    mineAt(5, 1)
                }

            val mineLocations =
                listOf(
                    Location(1, 4),
                    Location(1, 5),
                    Location(2, 1),
                    Location(4, 3),
                    Location(5, 1),
                )

            then("해당 위치에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5

                mineLocations.forEach { location ->
                    val cell = result.cells[location.toIndex(width)]
                    cell.hasMine() shouldBe true
                }
            }

            then("지뢰가 아닌 셀들에 인접 지뢰 개수를 표시할 수 있다") {
                // -1 은 지뢰
                val expectedAdjacentMines =
                    listOf(
                        listOf(1, 1, 1, -1, -1),
                        listOf(-1, 1, 1, 2, 2),
                        listOf(1, 2, 1, 1, 0),
                        listOf(1, 2, -1, 1, 0),
                        listOf(-1, 2, 1, 1, 0),
                    )

                for (row in 1..height) {
                    for (col in 1..width) {
                        val cellIndex = Location(row, col).toIndex(width)
                        val cell = result.cells[cellIndex]
                        val expectedMineCount = expectedAdjacentMines[row - 1][col - 1]

                        if (expectedMineCount == -1) {
                            cell.hasMine() shouldBe true
                        } else {
                            cell.hasMine() shouldBe false
                            cell.adjacentMines shouldBe AdjacentMines(expectedMineCount)
                        }
                    }
                }
            }
        }

        `when`("4개의 서로 다른 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                    mineAt(1, 4)
                    mineAt(1, 5)
                    mineAt(2, 1)
                    mineAt(4, 3)
                }

            then("4개의 수동 위치와 1개의 랜덤 위치에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                        Location(1, 5),
                        Location(2, 1),
                        Location(4, 3),
                    )
                mineLocations.forEach { location ->
                    val cell = result.cells[location.toIndex(width)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("5개의 똑같은 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                    mineAt(1, 4)
                    mineAt(1, 4)
                    mineAt(1, 4)
                    mineAt(1, 4)
                    mineAt(1, 4)
                }

            then("1개의 수동 위치와 4개의 랜덤 위치에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                    )
                mineLocations.forEach { location ->
                    val cell = result.cells[location.toIndex(width)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("6개의 서로 다른 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                    mineAt(1, 4)
                    mineAt(1, 5)
                    mineAt(2, 1)
                    mineAt(4, 3)
                    mineAt(5, 1)
                    mineAt(5, 2)
                }

            then("처음에 받은 지뢰 위치 5개에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                        Location(1, 5),
                        Location(2, 1),
                        Location(4, 3),
                        Location(5, 1),
                    )

                mineLocations.forEach { location ->
                    val cell = result.cells[location.toIndex(width)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("1개의 보드 내 존재하지 않는 위치와 5개의 서로 다른 지뢰 위치를 수동으로 받아 보드를 만들면") {
            val result =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                    mineAt(0, 4)
                    mineAt(1, 4)
                    mineAt(1, 5)
                    mineAt(2, 1)
                    mineAt(4, 3)
                    mineAt(5, 1)
                }

            then("처음에 받은 지뢰 위치 5개에 지뢰를 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.hasMine() }.size shouldBe 5
                val mineLocations =
                    listOf(
                        Location(1, 4),
                        Location(1, 5),
                        Location(2, 1),
                        Location(4, 3),
                        Location(5, 1),
                    )

                mineLocations.forEach { location ->
                    val cell = result.cells[location.toIndex(width)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("5개의 서로 다른 오픈위치를 수동으로 받아 보드를 만들면") {
            val result =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                    openAt(1, 4)
                    openAt(1, 5)
                    openAt(2, 1)
                    openAt(4, 3)
                    openAt(5, 1)
                }

            val openLocations =
                listOf(
                    Location(1, 4),
                    Location(1, 5),
                    Location(2, 1),
                    Location(4, 3),
                    Location(5, 1),
                )

            then("해당 위치가 오픈된 셀을 가진 보드판을 만들 수 있다") {
                result.cells.filter { it.isOpen() }.size shouldBe 5

                openLocations.forEach { location ->
                    val cell = result.cells[location.toIndex(width)]
                    cell.isOpen() shouldBe true
                }
            }
        }
    }
})
