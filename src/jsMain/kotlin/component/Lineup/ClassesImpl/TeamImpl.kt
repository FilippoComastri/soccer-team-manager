package component.Lineup.ClassesImpl

import component.Lineup.Squad
import component.Lineup.Team

data class TeamImpl(
    override var squad: Squad
) : Team