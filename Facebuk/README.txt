1. FacebukObject is a superclass for all objects in Facebuk, with fields name and Image.
   Reason: all objects in Facebuk have name and Image, and accordingly, the setters and getters methods for those fields. 

2. LiveObject is a child of FacebukObject and a superclass of Pet and Person objects.
   Fields contained: a list of friends (_friends) and a list of moments (_moments) 
   Reason: pets and person shares two fields specified and a lot of common behaviors.
   Behaviors shared: 
     a. Getters and setters methods for list of friends and list of moments. 
     b. getFriendWithWhomIAmHappiest ()
     c. getOverallHappiestMoment()
     d. findMaximumCliqueOfFriends ()
     e. isClique (ArrayList set)

3. Owned Object is an interface (not sure if neccessary in this specific project).
   This interface is implemented by Pet and Possessions. 
   Reason: Pet and Possessions share some same behavior: 
     a. setOwner (Person owner) 
     b. getowner ()