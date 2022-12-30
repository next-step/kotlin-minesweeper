package dto

data class BoardDto(
    val rows: List<Row>
) : List<Row> by rows

data class Row(
    val cols: List<Col>
) : List<Col> by cols

data class Col(
    val value: String
) {
    override fun toString(): String {
        return value
    }
}
