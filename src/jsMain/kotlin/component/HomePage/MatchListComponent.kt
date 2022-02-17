package component.HomePage

import Match
import component.dialogs.insertResultDialog
import csstype.FlexGrow
import kotlinext.js.jso
import react.Props
import react.fc
import csstype.px
import mui.material.*
import react.dom.html.ReactHTML
import react.useState

external interface MatchListProps : Props {
    var onDelete: (Match)-> Unit
    var updatematch: (Match) -> Unit
    var matches: List<Match>
    var myteam: String
}

val matchList = fc<MatchListProps> { props ->
    var isOpenChangeDialog by useState(false)
    var selectedMatch: Match? by useState(null)
    Box {
        attrs {
            sx = jso {
                maxHeight=400.px
                flexGrow = FlexGrow(5.0)
            }

        }
        Typography {
            attrs {
                sx = jso {
                    marginTop = 10.px
                }
                align = TypographyAlign.center
                variant = "h4"
                component = ReactHTML.div
            }
            +"Matches"
        }
        List {
            props.matches.sortedByDescending(Match :: dayHour).forEach { m ->
                ListItem {
                    child(matchView) {
                        attrs {
                            myTeam = props.myteam
                            match = m
                            openChangeDialog = {
                                selectedMatch = m
                                isOpenChangeDialog = true
                            }
                            deleteMatch = { props.onDelete(m) }
                        }
                    }
                }

            }
        }
        selectedMatch?.let {  curr ->
            child(insertResultDialog) {
                attrs {
                    myTeam = props.myteam
                    match = curr
                    isOpenDialog = isOpenChangeDialog
                    closeForm = { isOpenChangeDialog=false }
                    submitForm = {
                            m -> props.updatematch(m)
                            isOpenChangeDialog = false
                    }
                }
            }
        }


    }


}