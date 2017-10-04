# FusePort
A small but hopefully growing teleportation plugin for Bukkit.

## Features:
- Nothing

### Planned:
- Request to teleport to someone
- Request someone to teleport to you
- Teleport to position
- On click commands as chat feedback
- Go back to your previous position
- Setting: Allow bypass or block requests completly, as default and per player
- Permissions: In a way muli-level bypass permissions, for direct teleports
- Abort teleportation by move/clicking + timer
- I18n
- Command name change

## Possible command setup:
- /tp - Teleport to a player. If insufficient permissions, request to teleport to him
- /tpr - Request to teleport to a player
- /tph - Teleport a player 'here'. If insufficient permissions, request to teleport a player 'here'
- /tphr - Request to tp a player 'here'
- /tpa - Accept latest* request
- /tpd - Deny latest* request
*latest: If no playername as argument given, its nessecary to select one of the pending ones, or complain
- /tpf - A force version of /tp which never triggers the request. (Better would be a parameter -f)
- /tphf - A force version of /tph which never triggers the request. (Better would be a parameter -f)

## Why another?
Since no other plugin known has all these features combined.
There are at least two specific servers (with custom solutions) which would gain by using this plugin.

## Internal (ToDo, Notes):

Which features will be improved on these two servers:
- Better commands (chat interface), also increase command consistence
- Multi-level bypass
- Setting: Default accept/request/deny
- Handling multiple requests at once

Other:
On most servers the command 'tpa' requests to teleport to a player. But most player have no clue what the 'a' stands for. In german there is the word 'Anfrage' which basically means 'request', germans can answer the question. But most english ones don't know a pretty word. Its either "If you use this command the other have to accept" or 'access' but nothing sounds logically. Some server gladly support the usage of 'tpr'. 
-> We will use the 'tpa' command for 'teleport accept', which is a big different to most teleportation systems.


PS: If you know a plugin which provides all these features, let 'me' know :)