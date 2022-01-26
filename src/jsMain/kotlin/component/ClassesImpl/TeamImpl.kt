package component.ClassesImpl

import component.Squad
import component.Team

data class TeamImpl(
    override var squad: Squad
) : Team