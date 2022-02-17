package component.PlayerStatsPage

import Player
import csstype.*
import kotlinext.js.jso
import kotlinx.html.SMALL
import mui.icons.material.DeleteForever
import mui.material.*
import mui.material.Size
import react.Props
import react.dom.aria.ariaLabel
import react.dom.html.ReactHTML
import mui.material.Table
import mui.material.Typography
import react.fc
import react.key
import react.useState

external interface RowProps : Props {
    var player : Player
    var onClickPlayer: (Player) -> Unit
    var onDelete: () -> Unit
}

val rowCollapsible = fc<RowProps> { props ->
    var open by useState(false)
    TableRow {
        attrs {
            key = props.player.surname
            onClick = {
                props.onClickPlayer(props.player)
            }
        }
        TableCell {
            IconButton {
                attrs {
                    size = Size.small
                    onClick = { open = !open }
                }
                when {
                    open -> mui.icons.material.KeyboardArrowUp()
                    else -> mui.icons.material.KeyboardArrowDown()
                }
            }
        }
        TableCell {
            attrs {
                component = ReactHTML.th
                scope = "row"
            }
            +props.player.surname
        }
        TableCell {
            attrs {
                align = TableCellAlign.right
            }
            +"${props.player.name}"
        }
        TableCell {
            attrs {
                align = TableCellAlign.right
            }
            +"${props.player.shirtNumber}"
        }
        TableCell {
            attrs {
                align = TableCellAlign.right
            }
            for (r in props.player.roles) {
                +"${r} "
            }
        }
        TableCell {
            attrs {
                align = TableCellAlign.right
            }
            +"${props.player.averageVote}"
        }
        TableCell {
            attrs {
                align = TableCellAlign.right
            }
            +"${props.player.marketvalue}"
        }
        TableCell {
            IconButton {
                attrs {
                    size = Size.small
                    onClick = { props.onDelete() }
                }
                DeleteForever()
            }
        }
    }
    TableRow {
        TableCell {
            attrs {
                sx = jso {
                    paddingBottom = 0.px
                    paddingTop = 0.px
                }
                colSpan = 7
            }
            Collapse {
                attrs {
                    `in` = open
                    timeout = "auto"
                }
                Box {
                    attrs.sx = jso {
                        flexGrow = FlexGrow(1.0)
                        maxWidth = 650.px
                        maxHeight = 400.px
                        margin = 1.px
                    }
                    Typography {
                        attrs {
                            variant = "h6"
                            gutterBottom = true
                            component = ReactHTML.div
                        }
                        +"Players' matches"
                    }
                    Table {
                        attrs.size = Size.small
                        TableHead {
                            TableRow {
                                TableCell {
                                    attrs {
                                        align = TableCellAlign.center
                                    }
                                    +"Opponent"
                                }
                                TableCell {
                                    attrs {
                                        align = TableCellAlign.center
                                    }
                                    +"Date"
                                }
                            }
                        }
                        TableBody {
                            for (m in props.player.matches) {
                                TableRow {
                                    TableCell {
                                        attrs {
                                            align = TableCellAlign.center
                                        }
                                        +"${m.opponent}"
                                    }
                                    TableCell {
                                        attrs {
                                            align = TableCellAlign.center
                                        }
                                        +"${m.dayHour}"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}