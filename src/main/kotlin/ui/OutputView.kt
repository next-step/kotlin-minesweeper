package ui

import domain.MineField
import domain.Slot

object OutputView {
    private const val REQUEST_HEIGHT = "높이를 입력하세요."
    private const val REQUEST_WIDTH = "너비를 입력하세요."
    private const val REQUEST_NUMBER_OF_MINES = "지뢰는 몇개 인가요?"
    private const val TITLE_START_GAME = "지뢰찾기 게임 시작"
    private const val COVERED_SLOT = "c "
    private const val MINE = "* "
    private const val GROUND_FORMAT = "%d "

    fun displayRequestHeight() = println(REQUEST_HEIGHT)

    fun displayRequestWidth() = println(REQUEST_WIDTH)

    fun displayRequestNumberOfMines() = println(REQUEST_NUMBER_OF_MINES)

    fun displayGameStartTitle() = println(TITLE_START_GAME)

    fun displayMineField(mineField: MineField) {
        val allSlots = mineField.allSlots()
        allSlots.forEach(::printSlotLine)
    }

    private fun printSlotLine(slotList: List<Slot>) {
        with(StringBuilder()) {
            slotList.map(::slotToDisplay)
                .forEach(::append)
            println(toString())
        }
    }

    private fun slotToDisplay(slot: Slot): String {
        if (!slot.isChecked)
            return COVERED_SLOT
        if (slot.isMine())
            return MINE
        return GROUND_FORMAT.format(slot.numberOfNearMines())
    }
}
