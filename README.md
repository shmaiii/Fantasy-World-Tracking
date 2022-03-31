# WORLD MAP FANTASY

## Intro

This application is inspired by my trip from winter break, when I visited my 
sister in Finland and went on an EU trip with her to 7 countries in a week. After my trip,
I thought it would be cool to have an app that tracks all the places that I set foot on.
But as such apps do exist in App Store (i.e, Places Been), I thought to myself, then what about an 
app that instead of keeping tracks of countries I have been to, it would store fantasy worlds that I
used to experience? Like Harry Potter World, The Hobbit World, Adventure Time World?

## Details

**What will the application do?**
- Stores fantasy worlds in categories including *cartoon, movies, books, games*
- Users can 'unlock'/open a world if they experienced that world (from books, movies, etc)
- Keep tracks of the worlds users have been to
- List the worlds users been to in 

**Target users:** anyone who is a big fan of fantasy books, fantasy movies, who are obsessed with 
fantasy worlds (Potterheads, Alice in Wonderland fans, etc...)

**Why this project?**
- Because I like different worlds in fantasy books
- Because fantasy and traveling is fun!! I want to combine my two interests for this project
- COVID is going to prevent people from traveling, so let's 'travel' through pages and movies!
- I'm learning to create an application with a topic I really like!

##User Stories
- As a user, I want to be able to create and add a fantasy world to my world list in appropriate category
- As a user, I want to be able to mark a fantasy world as "been to", "want to", and "favourite".
- As a user, I want to be able to view the lists of worlds I have been to, want to go to, and favourite 
- As a user, I want to be able to view all worlds inside (both been to and not) 
- As a user, I want to be able to delete a world from my world list, or remove any list "been to", "want to", or "fav"
- As a user, I want to be able to select a category and view all worlds inside it. 
- As a user, I want to be able to automatically save the current state of the app, including all worlds I created and
all the lists I made changes to, after everytime I make change
- As a user, I want to be able to load the existing worlds and lists from file automatically everytime I restart
the program

##Phase 4 : Task 2
Wed Mar 30 18:18:54 PDT 2022 \
Store Adventure Time with category CARTOON \
Wed Mar 30 18:18:54 PDT 2022 \
Store Naruko with category CARTOON \
Wed Mar 30 18:18:54 PDT 2022 \
Store 25 21 with category MOVIE \
Wed Mar 30 18:18:54 PDT 2022 \
Store Business Proposal with category MOVIE \
Wed Mar 30 18:18:54 PDT 2022 \
Store MAUS with category BOOK \
Wed Mar 30 18:18:54 PDT 2022 \
Add 25 21 to a sublist \
Wed Mar 30 18:18:54 PDT 2022 \
Add Adventure Time to a sublist \
Wed Mar 30 18:18:54 PDT 2022 \
Add Naruko to a sublist \
Wed Mar 30 18:18:54 PDT 2022 \
Add 25 21 to a sublist \
Wed Mar 30 18:19:16 PDT 2022 \
Store Totoro with category CARTOON \
Wed Mar 30 18:19:26 PDT 2022 \
Store Reply 1988 with category MOVIE \
Wed Mar 30 18:19:36 PDT 2022 \
Add Reply 1988 to a sublist \
Wed Mar 30 18:19:38 PDT 2022 \
Add Reply 1988 to a sublist \
Wed Mar 30 18:19:52 PDT 2022 \
Remove Naruko from a sublist

##Phase 4 : Task 3
I'm pretty satisfied with the design of classes in the model and persistence package, with the existing functionalities.
They are pretty neat and clean, with not a lof of code duplications. It makes sense to me to have a FantasyWorld class, 
and an AllFantasyWorld class that contains lists of FantasyWorld and definition of core functions that act on these 
lists.

I think there are a lot of rooms for improvement for the design of the ui package. If I have more time, I believe it is
worth changing the associations of WorldApp, HomePage, ViewWorld, and ViewCat classes. I'm thinking of:
- Making WorldApp, HomePage, ViewWorld extend the WorldApp class 
- Making WorldApp extend JFrame. 
- Using a same frame for WorldApp, HomePage, and ViewWorld instead of initializing a new frame and re-setup the frame
each time I want to open a new window/viewing option. 

These will help me get rid of code duplications to set up a frame, and the complications of attaching a WorldApp object
each class.

Other than the aforementioned class relationships, I would want to:

- Make the common color pattern (like purple background) a final variable to adopt single point of control
- Improve coupling by reducing code copy (like grouping common visualization methods into one method and move it to the
higher class in the hierarchy (after implementing the hierarchy), improving the setup and properties methods, etc)
- Try to apply Observer Pattern so that when I have some event in the WorldApp (like store a world, delete a world,
change a sublist,etc), the world state will update the changes without my having to add the deleting/removing worlds to
a queue to avoid ConcurrentModificationException. (The queue while resolves the exception problem, requires code 
duplication in the ViewWorld app)