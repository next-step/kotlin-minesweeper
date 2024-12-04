package map

class Rows(
    val rows: List<Columns>,
) {
    companion object {
        fun ready(
            height: Height,
            width: Width,
        ): Rows = Rows(rows = List(height.size) { index -> Columns.ready(width = width, rowIndex = index) })
    }
}
