# The Protocol

This is a document describing the protocol for our version of tichu.

## Design

The server should do most of the heavy-lifting. It distributres the cards,
controls the game-loop, and counts the points. The clients are only
responsible for asking the player for their next move.

The server always initiates a request to a player and waits for a response.
It does this by first sending a string to specify what the server wants to do
and then sending any data that the client might require for the request. For
example, it might send the string "get-move" and then the list of cards that the
player has in their hand. Then it waits for the client to send back a move.

## Requests

**start**

Inform the client that the game will begin.

```
start
```

**move-made**

Inform the client of a move made by another player.

```
move-made
<player:String>
<move:Move>
```

**get-move**

Request a move from a player.
Optionally informs the player why a previous move was requested by sending a string
in place of `<rejection>`. If there is no rejection, `null` is sent.

```
get-move
<cards:Card[]>
<rejection:String?"null">
```

**ended**

Inform the player of the game having ended.

```
ended
<won:boolean>
<points:int>
<enemy-points:int>
```
