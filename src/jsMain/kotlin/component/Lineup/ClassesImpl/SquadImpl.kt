package component.Lineup.ClassesImpl

import component.Lineup.Player
import component.Lineup.Squad

data class SquadImpl(
    override var gk: Player,
    override var df: Array<Player>,
    override var cdm: Array<Player>,
    override var cm: Array<Player>,
    override var cam: Array<Player>,
    override var fw: Array<Player>
): Squad {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as SquadImpl

        if (gk != other.gk) return false
        if (!df.contentEquals(other.df)) return false
        if (!cdm.contentEquals(other.cdm)) return false
        if (!cm.contentEquals(other.cm)) return false
        if (!cam.contentEquals(other.cam)) return false
        if (!fw.contentEquals(other.fw)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gk.hashCode()
        result = 31 * result + df.contentHashCode()
        result = 31 * result + cdm.contentHashCode()
        result = 31 * result + cm.contentHashCode()
        result = 31 * result + cam.contentHashCode()
        result = 31 * result + fw.contentHashCode()
        return result
    }
}