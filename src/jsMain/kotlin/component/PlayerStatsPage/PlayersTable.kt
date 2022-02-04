package component.PlayerStatsPage

import Player
import csstype.FlexGrow
import kotlinext.js.jso
import csstype.px
import mui.material.Paper
import mui.material.*
import react.*
import react.dom.aria.ariaLabel


external interface PlayersTableProps : Props {
    var players : List<Player>
    var onSelectPlayer : (Player) -> Unit
    var deleteplayer : (Player) -> Unit
}

val playersTable = fc<PlayersTableProps> { props ->
    Box {
        attrs {
            sx = jso {
                flexGrow = FlexGrow(2.0)
                maxWidth = 700.px
                maxHeight = 400.px
            }
        }
        Divider {
            attrs {
                variant = DividerVariant.fullWidth
            }
            Chip {
                attrs {
                    label = ReactNode("Players")
                }

            }
        }
        TableContainer {
            attrs {
                component = Paper
                sx = jso {
                    maxHeight = 400.px
                }
            }
            Table {
                attrs {
                    sx = jso {
                        minWidth = 650.px
                    }
                    ariaLabel = "collapsible table"
                }
                TableHead {
                    TableRow {
                        TableCell{}
                        TableCell {
                            +"Surname"
                        }
                        TableCell {
                            attrs {
                                align = TableCellAlign.right
                            }
                            +"Name"
                        }
                        TableCell {
                            attrs {
                                align = TableCellAlign.right
                            }
                            +"Shirt Number"
                        }
                        TableCell {
                            attrs {
                                align = TableCellAlign.right
                            }
                            +"Roles"
                        }
                        TableCell {
                            attrs {
                                align = TableCellAlign.right
                            }
                            +"Average vote"
                        }
                        TableCell {
                            attrs {
                                align = TableCellAlign.right
                            }
                            +"Market Value"
                        }
                    }
                }
                TableBody {
                    for (p in props.players) {
                        child(rowCollapsible) {
                            attrs {
                                player = p
                                onClickPlayer = props.onSelectPlayer
                                onDelete = { props.deleteplayer(p) }
                            }
                        }
                    }

                }
            }

        }
    }
}