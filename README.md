# Penguin Solitaire

Penguin Solitaire is a Java applet based on the solitaire card game, Penguin.
![Penguin Solitaire Starting](https://github.com/mattleung10/PenguinSolitaire/blob/master/examples/PenguinSolitaire1.png)
## To Play

Download all the files, and run the `Main.java` file.

### Packages Required

`java.applet` (Java Applet Package), `java.awt` (Java Abstract Window Toolkit), and `java.util` (Java Util Package)

## Class Hierarchy

```
├── ShapeClass (abstract)
|   ├── SuitClass (abstract)
|   |   ├── DiamondClass
|   |   ├── HeartClass
|   |   ├── ClubClass
|   |   └── SpadeClass
|   ├── CardClass
|   └── DeckClass
|       ├── FlipperClass
|       └── FoundationClass
└── DialogClass
```

## Game Play Description
Penguin Solitaire uses a standard 52 card deck. There is a potential reserve of seven cards called the flipper, along the top. There are four piles of cards on the left side, called the foundation piles.
### Setup
A random rank of card is selected, and the four cards of the same rank are removed from the deck. This rank is called the “beak”. Three of those cards are placed into three of the four foundation piles. The other remaining 48 cards are shuffled and the remaining beak card is placed at the top of the deck. These 49 cards are then dealt from left to right into seven columns, each with seven rows; these 49 cards are called the layout. In the beginning, the flipper is empty, but each of the spaces can be filled with one card at a time for temporary storage, when cards are moving between the layout and foundation piles.
### Objective
The object of the game is to build the foundation piles up in rank, up to the card that is a rank lower than the beak. For example, if the beak is a jack, the last card of each foundation should be a ten.
### Play
On each play, you can move cards from the layout to the foundation and flipper. Once an appropriate card is placed onto a foundation pile, it cannot be removed. The bottom (surface) card of each column is available for play, and its removal releases the next card up in the column. Cards are moved one at a time, unless a suit sequence of cards is formed, which can be moved as a unit. When a column is emptied, you may fill the space it leaves with a card one rank lower than the rank of the beak, together with any other cards attached to it in descending suit-sequence. 

