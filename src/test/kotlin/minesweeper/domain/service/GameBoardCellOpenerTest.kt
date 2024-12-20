package minesweeper.domain.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Location
import minesweeper.domain.fiveByFiveCellsWithFiveLandmines
import minesweeper.domain.fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell
import minesweeper.domain.fiveByFiveCellsWithFiveLandminesAndOneFourLandmineCell
import minesweeper.domain.fiveByFiveCellsWithFiveLandminesOneOneNumberCellAndAdjacentNumberCell

class GameBoardCellOpenerTest : BehaviorSpec({
    val sut = GameBoardCellOpener()

    given("5x5 닫힌 셀, 지뢰 개수 5개의 게임판에서") {
        val gameBoard = GameBoard.from(fiveByFiveCellsWithFiveLandmines)

        `when`("(1,5) 위치(닫힌 셀. 지뢰 X, 인접 지뢰 개수 2개)를 열면") {
            val location = Location(row = 1, column = 5)
            val result = sut.openGameBoardCell(gameBoard, location)

            then("(1,5)의 닫힌 셀만 숫자 셀로 열린다") {
                result.cells shouldContainExactlyInAnyOrder fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell
            }
        }

        `when`("(1,4) 위치(닫힌 셀. 지뢰 O, 인접 지뢰 개수 1개)를 을 열면") {
            val location = Location(row = 1, column = 4)
            val result = sut.openGameBoardCell(gameBoard, location)

            then("(1,4)의 닫힌 셀만 지뢰 셀로 열린다") {
                result.cells shouldContainExactlyInAnyOrder fiveByFiveCellsWithFiveLandminesAndOneFourLandmineCell
            }
        }

        `when`("(1,1) 위치(닫힌 셀. 지뢰 x, 인접 지뢰 개수 0개)를 을 열면") {
            val location = Location(row = 1, column = 1)
            val result = sut.openGameBoardCell(gameBoard, location)

            then("지뢰가 없는 인접한 셀들(9칸) 모두 열린다") {
                result.cells shouldContainExactlyInAnyOrder fiveByFiveCellsWithFiveLandminesOneOneNumberCellAndAdjacentNumberCell
            }
        }

        `when`("게임판을 벗어난 위치를 열려고 하면") {
            val location = Location(row = 6, column = 1)

            then("논리적 오류이므로 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    sut.openGameBoardCell(gameBoard, location)
                }
            }
        }

        `when`("이미 열린 셀 위치로 다시 열려고 하면") {
            val alreadyOpenedGameBoard = GameBoard.from(fiveByFiveCellsWithFiveLandminesAndOneFiveNumberCell)
            val location = Location(row = 1, column = 5)
            val result = sut.openGameBoardCell(alreadyOpenedGameBoard, location)

            then("동일한 게임판을 반환한다") {
                result.cells shouldContainExactlyInAnyOrder alreadyOpenedGameBoard.cells
            }
        }
    }
})
