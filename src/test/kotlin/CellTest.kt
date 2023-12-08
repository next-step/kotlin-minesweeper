import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class CellTest : BehaviorSpec ({
    given("a cell") {
        `when`("is open") {
            then("should not be able to open") {
                shouldThrow<IllegalStateException> { Cell(state = CellState.Open).open() }
                    .message.shouldContain("cannot open a open cell")
            }
        }

        `when`("is open") {
            then("should display correct display value") {
                val adjacentMineCount = 3
                Cell(state = CellState.Open, adjacentMineCount = adjacentMineCount).displayValue shouldBe adjacentMineCount.toString()
            }
        }

        `when`("is closed") {
            then("should display correct display value") {
                Cell().displayValue shouldBe Cell.CLOSE_DISPLAY_CHARACTER
            }
        }

        `when`("is closed") {
            then("should be able to open") {
                val cell = Cell()
                cell.open()
                cell.state shouldBe CellState.Open
            }
        }

        `when`("adjacent mine count is invalid") {
            then("should throw exception") {
                shouldThrow<IllegalArgumentException> { Cell(adjacentMineCount = -1) }
                    .message.shouldContain("adjacent mine count should be in 0..8")

                shouldThrow<IllegalArgumentException> { Cell(adjacentMineCount = 9) }
                    .message.shouldContain("adjacent mine count should be in 0..8")
            }
        }
    }
})

data class Cell(
    val isMine: Boolean = false,
    val adjacentMineCount: Int = 0,
    var state: CellState = CellState.Close
) {
    init {
        require(adjacentMineCount in 0..8) { "adjacent mine count should be in 0..8 $adjacentMineCount of $this" }
    }

    val displayValue = if (state == CellState.Close) CLOSE_DISPLAY_CHARACTER else adjacentMineCount.toString()

    fun open() {
        check(state != CellState.Open) { "cannot open a open cell $this" }
        state = CellState.Open
    }

    companion object {
        const val CLOSE_DISPLAY_CHARACTER = "C"
    }
}

enum class CellState {
    Open, Close
}
