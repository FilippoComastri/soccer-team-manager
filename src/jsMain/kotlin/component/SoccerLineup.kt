@file:JsModule("react-soccer-lineup")
@file:JsNonModule

package component

import react.ComponentClass
import react.Props

@JsName("default")
external val soccerLineup: ComponentClass<SoccerLineupProps>

external interface SoccerLineupProps : Props {
    var color: String
    var size: String
    var pattern: String
    var homeTeam: Team
    var awayTeam: Team
}

external interface Player {
   var name: String
   var number: Number
   var color: String
   var numberColor: String
   var nameColor: String
   //var onClick: Function<Nothing>
}
/*external interface Style {
    var color: String
    var numberColor: String
    var nameColor: String
}*/

external interface Team {
    var squad: Squad
    //var style: Style
}

external interface Squad {
    var gk: Player
    var df: Array<Player>
    var cdm: Array<Player>
    var cm: Array<Player>
    var cam: Array<Player>
    var fw: Array<Player>
}



