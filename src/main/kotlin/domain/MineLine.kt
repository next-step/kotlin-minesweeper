package domain

import domain.ExceptionTypes.SLOT_CHECK_REQUEST_NOT_OVER_SIZE

class MineLine(private val row: List<Slot>) {

    fun isMineAt(position: Int): Boolean {
        require(position < row.size) { SLOT_CHECK_REQUEST_NOT_OVER_SIZE }
        return row[position].isMine()
    }

    fun isCheckedAt(position: Int): Boolean {
        checkIsRequestedPositionInRange(position)
        return row[position].isChecked()
    }

    fun changeCheckedAt(position: Int) {
        checkIsRequestedPositionInRange(position)
        row[position].changeChecked()
    }

    fun toList() = row.toList()

    fun numberOfNearMinesAt(position: Int): Int {
        checkIsRequestedPositionInRange(position)
        return row[position].numberOfNearMines()
    }

    fun size() = row.size

    private fun checkIsRequestedPositionInRange(position: Int) =
        require(position < row.size) { SLOT_CHECK_REQUEST_NOT_OVER_SIZE }
}
