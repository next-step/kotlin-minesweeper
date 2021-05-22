package domain

import domain.square.Square

class Row(private val squares: List<Square>) : List<Square> by squares
