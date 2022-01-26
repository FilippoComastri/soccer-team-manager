package component

import Player
import csstype.*
import kotlinext.js.jso
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
                    ariaLabel = "expand row"
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
    }
    TableRow {
        TableCell {
            attrs {
                sx = jso {
                    paddingBottom = 0.px
                    paddingTop = 0.px
                }
                colSpan = 2
            }
            Collapse {
                attrs {
                    `in` = open
                    timeout = "auto"
                }
                Box {
                    attrs.sx = jso { margin = 1.px }
                    Typography {
                        attrs {
                            variant = "h6"
                            gutterBottom = true
                            component = ReactHTML.div
                        }
                        +"Players' matches"
                    }
                    Table {
                        TableHead {
                            TableRow {
                                TableCell {
                                    attrs {
                                        align = TableCellAlign.right
                                    }
                                    +"Opponent"
                                }
                                mui.material.TableCell {
                                    attrs {
                                        align = TableCellAlign.right
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
                                            align = TableCellAlign.right
                                        }
                                        +"${m.opponent}"
                                    }
                                    mui.material.TableCell {
                                        attrs {
                                            align = TableCellAlign.right
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