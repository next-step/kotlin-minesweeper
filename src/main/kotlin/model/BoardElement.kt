package model

import model.minemark.MineMark

data class BoardElement(
    val position: Position,
    val mineMark: MineMark,
    val openStatus: OpenStatus = OpenStatus.CLOSED,
)
