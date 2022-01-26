import kotlinx.serialization.Serializable

@Serializable
data class MatchPlayerStatistics(
    val matchId: Int,
    val playerId: Int,
    val madeGoals: Int,
    val concedeGoals: Int,
    val playedMins: Int,
    val vote: Double,
    val yellowCards: Int,
    val redCards: Int
)
{
    companion object {
        const val path = "/playerStatistics"
    }
}
