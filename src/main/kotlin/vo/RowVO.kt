package vo

import domain.Row

class RowVO(
    val cells: List<String>,
) {
    companion object {
        operator fun invoke(row: Row): RowVO {
            return RowVO(
                row.map {
                    if (it.isClosed()) "C" else it.aroundMineCount().count.toString()
                }
            )
        }
    }
}
