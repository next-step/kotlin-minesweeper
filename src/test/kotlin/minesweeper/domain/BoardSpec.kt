package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.view.OutputView

private operator fun List<Cells>.get(position: Position): Cell {
    return this[position.y][position.x]
}

fun cells(vararg cells: Cell): Cells = Cells(cells.toList())
fun board(
    cells: List<Cells> = listOf(
        cells(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
        cells(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
        cells(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
    ),
    minePositions: List<Position> = emptyList(),
): Board = Board(cells.toList(), minePositions)

class BoardSpec : DescribeSpec(
    {
        describe("게임 보드 생성 검증 (생성자)") {
            context("cells 가 주어지면") {
                it("보드를 생성할 수 있다.") {
                    shouldNotThrowAny {
                        Board(
                            cells = listOf(
                                cells(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
                                cells(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
                                cells(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
                            ),
                            minePositions = emptyList(),
                        )
                    }
                }
            }

            context("높이가 0인 cells 가 주어지면") {
                it("보드를 생성할 수 없다.") {
                    shouldThrowExactly<IllegalArgumentException> {
                        Board(
                            cells = emptyList(),
                            minePositions = emptyList(),
                        )
                    }
                }
            }

            context("너비가 0인 cells 가 주어지면") {
                it("보드를 생성할 수 없다.") {
                    shouldThrowExactly<IllegalArgumentException> {
                        Board(
                            cells = listOf(
                                cells(),
                            ),
                            minePositions = emptyList(),
                        )
                    }
                }
            }

            context("각 행의 너비(셀 개수)가 다르면") {
                it("보드를 생성할 수 없다.") {
                    shouldThrowExactly<IllegalArgumentException> {
                        Board(
                            cells = listOf(
                                cells(Normal(0, 0), Normal(1, 0), Normal(2, 0)),
                                cells(Normal(0, 1), Normal(1, 1)),
                            ),
                            minePositions = emptyList(),
                        )
                    }
                }
            }
        }

        describe("'칸' 오픈 검증") {
            context("일반 칸을 오픈하면") {
                val board = board(
                    cells = listOf(
                        cells(Mine(0, 0), Mine(1, 0), Normal(2, 0)),
                        cells(Normal(0, 1), Mine(1, 1), Normal(2, 1)),
                        cells(Mine(0, 2), Mine(1, 2), Normal(2, 2)),
                    ),
                    minePositions = listOf(
                        Position(0, 0),
                        Position(1, 0),
                        Position(1, 1),
                        Position(0, 2),
                        Position(1, 2),
                    ),
                ).also { it.open(Position(2, 0)) }

                it("게임에 패배하지 않는다.") {
                    board.isLose() shouldBe false
                }

                it("지뢰가 없는 인접한 칸이 모두 열린다.") {
                    (board.cells[Position(2, 0)] as Normal).isOpened shouldBe true
                    (board.cells[Position(2, 1)] as Normal).isOpened shouldBe true
                    (board.cells[Position(2, 2)] as Normal).isOpened shouldBe true
                    (board.cells[Position(0, 1)] as Normal).isOpened shouldBe false
                }
            }

            context("지뢰 칸을 오픈하면") {
                val board = board(
                    cells = listOf(
                        cells(Mine(0, 0), Normal(1, 0), Normal(2, 0)),
                        cells(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
                        cells(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
                    ),
                    minePositions = listOf(Position(0, 0)),
                ).also { it.open(Position(0, 0)) }

                it("게임에 패배한다.") {
                    board.isLose() shouldBe true
                }
            }
        }

        describe("게임 승리 여부 검증") {
            context("게임에서 승리하면") {
                val board = board().also { it.win() }
                it("게임 승리 여부는 '참'이다.") {
                    board.isWin() shouldBe true
                }
            }

            context("게임에서 패배하면") {
                val board = board().also { it.lose() }
                it("게임 승리 여부는 '거짓'이다.") {
                    board.isWin() shouldBe false
                }
            }
        }

        describe("게임 패배 여부 검증") {
            context("게임에서 승리하면") {
                val board = board().also { it.win() }
                it("게임 패배 여부는 '거짓'이다.") {
                    board.isLose() shouldBe false
                }
            }

            context("게임에서 패배하면") {
                val board = board().also { it.lose() }
                it("게임 패배 여부는 '참'이다.") {
                    board.isLose() shouldBe true
                }
            }
        }

        describe("게임 종료 여부 검증") {
            context("게임에서 승리하면") {
                val board = board().also { it.win() }
                it("게임 종료 여부는 '참'이다.") {
                    board.isNotGameOver() shouldBe false
                }
            }

            context("게임에서 패배하면") {
                val board = board().also { it.lose() }
                it("게임 종료 여부는 '참'이다.") {
                    board.isNotGameOver() shouldBe false
                }
            }

            context("게임이 진행 중이면") {
                val board = board()
                it("게임 종료 여부는 '거짓'이다.") {
                    board.isNotGameOver() shouldBe true
                }
            }
        }

        describe("게임 종료 검증") {
            context("지뢰 칸을 오픈하면") {
                val board = board(
                    cells = listOf(
                        cells(Mine(0, 0), Normal(1, 0), Normal(2, 0)),
                        cells(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
                        cells(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
                    ),
                    minePositions = listOf(Position(0, 0)),
                ).also { it.open(Position(0, 0)) }

                it("게임 패배로 종료된다.") {
                    board.isLose() shouldBe true
                    board.isNotGameOver() shouldBe false
                }
            }

            context("일반 칸을 모두 오픈하면") {
                val board = board(
                    cells = listOf(
                        cells(Mine(0, 0), Normal(1, 0), Normal(2, 0)),
                        cells(Normal(0, 1), Normal(1, 1), Normal(2, 1)),
                        cells(Normal(0, 2), Normal(1, 2), Normal(2, 2)),
                    ),
                    minePositions = listOf(Position(0, 0)),
                ).also {
                    it.open(Position(1, 0))
                }

                OutputView.printBoard(board)

                it("게임 승리로 종료된다.") {
                    board.isWin() shouldBe true
                    board.isNotGameOver() shouldBe false
                }
            }
        }
    },
)
