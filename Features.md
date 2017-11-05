# Features and design of the plugin.

# Implemented:

## Commands:
- /tp
- /tpa - Accept pending request
- /tpd - Deny pending request

## Feedback:
- Each string has one MessageKey "bla.bla.blub"
- Internal languages will be stored to data-folder if not present and also loaded from the data-folder

## Request:
- Requests will be send when insufficient permissions for teleport
- /tp requests will overwrite /tph requests and the other way round
- /tp requests will overwrite other /tp requests

## Rules:
- Player can set thier default 'answer', and set a default answer for each player.

# TODO:

## Request System:
- Config: TTL of requests (Default: 2 minutes)
- Config: Time until a request will be considered as ignored (For the accept/deny with no argument)

## Feedback:
- Config: Prefix
- Config: Multilang or single language
- Config: Primary language
- Config: Optional auto-formatting
- Each code has set keywords <player1> <playerTo> which will be replaced with information
- Depending on the complexity of information, adding framework to auto-format chat output (to investigate)
- Chat on-click (JSON text), interactive chat
- MessageKey-Tree can have values for knots, which can be overwritten by lower knots, or leafes (move to later, since it turned out to be a bit more complex than intended)

## Commands:
- Config: Allow to change all command names (except /fuseport), maybe even subcommands
- /tpb - Back to last position(s) [see ##Location History]
- /tph - Teleport player to you
- /tpp - Teleport to position
- ^Allow relative positions
- All upper teleports by a third person
- Allow the usage of -force/-request in all teleportation commands. Force will only teleport if possible and not send a request. Request will always send a request, even if permissions for a direct teleport are given.
- All commands should print help on wrong usage
- Spam protection
- Permissions for everything

## Location Histroy:
- Config: White/Blacklist for worlds to save...
- Config: Amount of locations to store
- Config: Listen to teleport event
- Command: Save current position
- Store all teleports caused by FusePort
- Filter locations ¿pre-post? calling command (Not alowing specific worlds).

## Levels:
- Level per player
- Upper level can direct tp to the lower ones
- Change Levels to optional groups

## Rules:
- Config: Default deny/apply/prompt
- Ignore mode (deny without telling)

## Teleportation:
- Config: Time till teleport (Default: off/2sec)
- Config: Block teleports to yourself
- Abort the teleport by moving
- Abort the teleport by clicking
- Teleport to yourself, will align you to the center of the block

# Notes & To investigate:
- Checks if player online when event get triggered -> Check if its canceled!!!
- World filtering for location history (how?)
- Auto-formatting for chat feedback (what would a good system be??)
- Exact teleport position (yes)
- Player filtering, hide them (API...)
- World limits (boundaries)
- Request for all kind of tp's (later)
