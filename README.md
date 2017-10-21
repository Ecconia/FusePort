# FusePort
A small but hopefully growing teleportation plugin for Bukkit.

Last stable build: None yet :(

Builds: Check latest Vx.x.x Tag in the master branch.

## Features:
Currently working on the core features, a list will be provided with the first major version.

For more information about features, please read the Feature.txt which is not an official document, more a sheet to remember all to-be implemented features.

## Why another Teleportation Plugin?
Since no other plugin known has all the here planned features combined.
There are at least two specific servers (with custom solutions) which would gain by using this plugin.

### Planned (Core features not complete):
Basic features and commands:
- Request to teleport to someone
- Request someone to teleport to you
- Teleport to position
- Go back to your previous position
- Abort teleportation by move/clicking + timer

"New"/Important:
- Setting: Allow bypass or block requests completly, as default and per player
- Permissions: In a way muli-level bypass permissions, for direct teleports
- On click commands as chat feedback (JSON-Text)
- Command name change
- I18n (multi-language support)

## Possible command setup:
- /tp - Teleport to a player. If insufficient permissions, request to teleport to him
- /tph - Teleport a player 'here'. If insufficient permissions, request to teleport a player 'here'
- maybe: /tpr /tphr - To force to send a request, even if bypass permissions present.
- /tpa /tpd - Accept/Deny request
- /tpb - Back to last position(s)
- /fuseport - Settings/reload/Player settings

## Install:
- Clone repository
- use 'mvn install' in the project directory (linux command)

For developing in eclipse:
- 'mvn eclipse:clean'
- 'mvn eclipse:eclipse'
- Import as maven project

## Internal (ToDo, Notes):

Which features will be improved on these two servers:
- Better commands (chat interface), also increase command consistence
- Multi-level bypass (Instead of only two hardcoded levels)
- Setting: Default accept/request/deny
- Handling multiple requests at once for one player

Other:
The command /tpa will not send a request for teleportation, since /tp will. Also 'a' is a nice short for 'accept'. It feels wrong and it is confusing to have /tpa for requests and /tpaccept to accept players. Most english speaking players don't even know what the 'a' in /tpa stands for, only a few could find a fitting word.

-> We/I will use the 'tpa' command for 'teleport accept', which is a big different to most teleportation systems.

Contribute:
I (Ecconia) am currently working alone on the project, taking part? Sure :)

I have not much experience with PR's though, for this stage of the project where nothing is completly fixed its probably better if you contact me directly (see contact below). For the beginning I will likly only accept features and ideas, code comments and suggestions are welcome too.

PS: If you know a plugin which provides all these features, let me (Ecconia) know :)

### Contact:
Skype: Ecconia
Email: fuseport@ecconia.com