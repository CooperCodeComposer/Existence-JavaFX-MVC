# Existence-JavaFX-MVC
Videogame using JavaFX and MVC architecture

- Java 8
- JavaFX 2.2

##The Game:
There are three kinds of life forms in the ecosystem: Red, Blue and Green
+ Red eat blue 
+ Blue eat green
+ Green eat red

You are The Protector. It's your job to maintain a balanced ecosystem for as long as possible.
Use ARROW KEYS to control The Protector.

+ If you come into contact with a life form it will self replicate
+ There must be at least 1 of each species alive at any time
+ Unfortunately, along the way you may encounter death forms. If you touch these you will die. 
 
##Features:
+ Model, View, Controller design pattern
+ Follows the interpretation of MVC outlined in "Head First Design Patterns" where views register as observers of the model layer. The model layer then notifies the views directly. 

![Alt text](/ExistenceSS.png?raw=true "")

##ToDo 
+ Add ability to show why gameOver occured and restart game
+ Add soundFX and music
+ Implement different levels and increasing difficulty 

# Author
Alistair Cooper

[twitter @SwiftComposer](https://www.twitter.com/swiftcomposer.com)

[SwiftCodeComposer.com](https://www.swiftcodecomposer.com)
