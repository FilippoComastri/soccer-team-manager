@file:JsModule("react-soccer-lineup")
@file:JsNonModule

package component.lineup

import react.ComponentClass
import react.Props

@JsName("default")
external val soccerLineup: ComponentClass<SoccerLineupProps>

external interface SoccerLineupProps : Props {
    var color: String
    var size: String
    var pattern: String
    var homeTeam: dynamic
}







