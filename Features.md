# Features and design of the plugin.

# Implemented:

# TODO:

## Location Histroy:
- Command: Save current position (suggestion)
- Command: Back to x latest position
- Config: White/Blacklist for worlds to save...
- Config: Amount of locations to store
- Config: Listen to teleport event
- Store all teleports caused by FusePort
- Alternative to worldblocks would be worldfiltering (to investigate)

## Request System:
- Config: TTL of requests (Default: 2 minutes)
- Config: Time until a request will be considered as ignored (For the accept/deny with no argument)
- Command: Deny pending request
- Command: Accept pending request
- Command note: If no player is passed to the two commands, we will have to prompt which pending to choose.
- Each player can only have one request, new request overwrites the old one

## I18n & Player feedback:
- Config: Prefix
- Config: Multilang or single language
- Config: Primary language
- Config: Optional auto-formatting
- Config files: Each language
- Each string has one code "bla.bla.blub"
- Each code has set keywords <player1> <playerTo> which will be replaced with information
- Depending on the complexity of information, adding framework to auto-format chat output (to investigate)
- Chat on-click (JSON text)

## Levels:
- Level per player
- Upper level can direct tp to the lower ones

## Command handler:
- Command: Teleport to player
- Command: Teleport player to you
- Command note: '-force' to not send a request, '-request' to only send a request
- Command note: If permissions/level are/is insufficient, will fallback to sending a request
- Command: Teleport to position
- Command: Teleport someone else
- Command note: Positional teleport should support relative positions
- Without any argument you will receive help

## Default answer setting:
- Config: Default deny/apply/prompt
- Setting per player and per level
- Default deny/apply or prompt
- Additional mode: deny silent (sends pretends to send request)

## Command name system:
- Config: Overwrites for command names

## Teleportation:
- Config: Time till teleport (Default: off/2sec)
- Abort the teleport by moving
- Abort the teleport by clicking
- Teleport to yourself, will align you to the center of the block

# Notes & To investigate:
- Checks if player online when event get triggered (really?)
- World filtering for location history (but how?)
- Auto-formatting for chat feedback (work or?)
- Exact teleport position (yeah!)
- Player filtering, hide them (API...)
- World limits (yeah!)
- Request for all kind of tp's (maybe?)