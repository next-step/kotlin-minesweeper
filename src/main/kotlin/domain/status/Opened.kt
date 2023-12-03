package domain.status

sealed interface Opened : SpotStatus {

    object Mine : Opened {
        override fun getSymbol(): String = "*"

        override fun mineTrapped(): Boolean = true
    }

    object Zero : Opened {
        override fun getSymbol(): String = "0"
    }

    object One : Opened {
        override fun getSymbol(): String = "1"
    }

    object Two : Opened {
        override fun getSymbol(): String = "2"
    }

    object Three : Opened {
        override fun getSymbol(): String = "3"
    }

    object Four : Opened {
        override fun getSymbol(): String = "4"
    }

    object Five : Opened {
        override fun getSymbol(): String = "5"
    }

    object Six : Opened {
        override fun getSymbol(): String = "6"
    }

    object Seven : Opened {
        override fun getSymbol(): String = "7"
    }

    object Eight : Opened {
        override fun getSymbol(): String = "8"
    }

    companion object {
        private val properties = listOf(
            Zero,
            One,
            Two,
            Three,
            Four,
            Five,
            Six,
            Seven,
            Eight
        ).associateBy { it.getSymbol().toInt() }

        fun from(nearMineCount: Int, hasMine: Boolean): Opened {
            return if (hasMine) Mine else properties[nearMineCount] ?: throw IllegalStateException()
        }
    }
}
