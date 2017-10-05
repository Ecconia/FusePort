# FusePort
A small but hopefully growing teleportation plugin for Bukkit.

Last stable build: None yet...

Builds: check latest Vx.x.x Tag.

## Features:
Working on the core features, a list will come with the first major version.

For more information about features, please read the Feature.txt
There is no official document for Features yet.

## Why another Teleportation Plugin?
Since no other plugin known has all the here planned features combined.
There are at least two specific servers (with custom solutions) which would gain by using this plugin.

### Planned (Core features not complete):
Normal:
- Request to teleport to someone
- Request someone to teleport to you
- Teleport to position
- Go back to your previous position
- Abort teleportation by move/clicking + timer

Important:
- Setting: Allow bypass or block requests completly, as default and per player
- Permissions: In a way muli-level bypass permissions, for direct teleports
- On click commands as chat feedback
- Command name change
- I18n

## Possible command setup:
- /tp - Teleport to a player. If insufficient permissions, request to teleport to him
- /tpr - Request to teleport to a player
- /tph - Teleport a player 'here'. If insufficient permissions, request to teleport a player 'here'
- /tphr - Request to tp a player 'here'
- /tpa - Accept request
- /tpd - Deny request
- /tpb - Back to last positions
- /fuseport - Settings reload control

## Install:
- Clone repository
- use 'mvn install' in the project

For developing in eclipse:
- 'mvn eclipse:clean'
- 'mvn eclipse:eclipse'
- Import as maven project

## Internal (ToDo, Notes):

Which features will be improved on these two servers:
- Better commands (chat interface), also increase command consistence
- Multi-level bypass
- Setting: Default accept/request/deny
- Handling multiple requests at once for one player

Other:
On most servers the command 'tpa' requests to teleport to a player. But most player have no clue what the 'a' stands for. In german there is the word 'Anfrage' which basically means 'request', germans can answer the question. But most english ones don't know a pretty word. Its either "If you use this command the other have to accept" or 'access' but nothing sounds logically. Some server gladly support the usage of 'tpr'. 

-> We will use the 'tpa' command for 'teleport accept', which is a big different to most teleportation systems.

Contribute:
I (Ecconia) am currently working alone on the project, taking part? Sure :)

I have not much experience with PR's thou, for this stage of the project where nothing is completly fixed its probably better if you contact me directly via Skype (Username: Ecconia) or Email (below). For the beginning I will likly only accept features and ideas. If its something code related, feel free to contact me, I will consider it.

PS: If you know a plugin which provides all these features, let 'me' know :)

### Contact:
Email: fuseport@ecconia.com