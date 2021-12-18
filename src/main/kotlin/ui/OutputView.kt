package ui

import domain.Slot

object OutputView {
    private const val REQUEST_HEIGHT = "높이를 입력하세요."
    private const val REQUEST_WIDTH = "너비를 입력하세요."
    private const val REQUEST_NUMBER_OF_MINES = "지뢰는 몇개 인가요?"
    private const val TITLE_START_GAME = "지뢰찾기 게임 시작"
    private const val COVERED_SLOT = "c "
    private const val MINE = "* "
    private const val GROUND_FORMAT = "%d "
    private const val REQUEST_INPUT_POINT_FOR_CHECK = "open: "
    private const val LOSE_GAME = "Lose Game. "
    private const val WIN_GAME = "Win Game. "

    fun displayLoseGame() = println(LOSE_GAME)

    fun displayWinGame() = println(WIN_GAME)

    fun displayRequestHeight() = println(REQUEST_HEIGHT)

    fun displayRequestWidth() = println(REQUEST_WIDTH)

    fun displayRequestNumberOfMines() = println(REQUEST_NUMBER_OF_MINES)

    fun displayGameStartTitle() = println(TITLE_START_GAME)

    fun displayRequestPointForCheck() = print(REQUEST_INPUT_POINT_FOR_CHECK)

    fun displayMineField(allSlots: List<List<Slot>>) = allSlots.forEach(::printSlotLine)

    private fun printSlotLine(slotList: List<Slot>) {
        with(StringBuilder()) {
            slotList.map(::slotToDisplay)
                .forEach(::append)
            println(toString())
        }
    }

    private fun slotToDisplay(slot: Slot): String {
        if (!slot.isChecked())
            return COVERED_SLOT
        if (slot.isMine())
            return MINE
        return GROUND_FORMAT.format(slot.numberOfNearMines())
    }
}
