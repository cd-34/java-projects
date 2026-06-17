* Whenever I "make all" in terminal now, it takes an extra 10s or so because of maven. I don't think it's downloading all of the dependencies but not really sure. 

* If I take a break from a project, I forget where methods are used, and how often they're used. I'm finding myself using Ctrl + Shift + F to find how often a method is used and where else it shows up. I know this falls under the whole "creating a simple interface so the reader knows its dependencies" category, but how do I apply that information? 

* (Related to above) For the builder, especially in build(), I feel like I'm bouncing around a lot and despite me writing it still kinda confuses me on the order and where the train of thought leads
    * Goes against Ousterhout's principles of being a deep module
    * Three classes are involved just to set up one task - adding players to the game
    * The preferable(?) goal would be to have one class that does all of the work
        * BlackjackApp would just orchestrate and call upon it after
What's the solution? Put the builder into Blackjack? Make the builder do more?

* dealerTurn() in Blackjack
    * Kinda requires a comment so that the reader knows it players ALL of a dealer's turns to completion and not just one card
    * Would this be too much of an interface for such a shallow method? Is the comment enough to suffice?

* Simplified explanation of FSM is:
    * State in this context is the different gamestates such as dealer turn, player turn, etc
    * How the program acts is dependent on the state
    * As a result, need to be diligent about making sure methods are only enacted once in the proper state
    * Also need to be diligent about making sure that after the appropriate methods, the state is changed