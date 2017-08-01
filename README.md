 # ToDo
Simple Android Application
Helps user to create a ToDo List including support for editing an item.
Submitted by: Rashmi Gupta
Time spent: 4 hours spent in total

# Completed user stories:

 Required: User can add and view a new task item to the list that is visible on main screen
 Required: User can click on a task in the list to edit the title text and persist the changes to the list
 Required: User can close the tasks that are done.  Closed tasks are removed from to do list, and list view is updated.
 Required: Application can persist todo items and retrieve them properly on app restart

 Notes: Spent some time making the data to persist in SQLite DB, showing edit dialog to add and update to do task item, and showing alert dialog when closing the to do task item.
 Challenges: Download for git.exe on Windows platform kept failing
 
 # Walkthrough of all user stories:
 <img src='http://i.imgur.com/1DQvZ3X.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

  GIF created with [LiceCap](http://www.cockos.com/licecap/).
  
  ## Project Analysis
In the last four years of my Android app development experience, initially it was frustrating. Despite the familiarity with XML, 
and Java it was not an easy platform to learn. It was difficult to find good explanations of the overall structuring, and terms 
such as activity, services, fragments, and layout resources. I have now fair grasp of Android development platform that I have learned
using many-many learning resources that became available. I can relate the components structuring in Android platform, and Android
application development using those components to many of the popular patterns in software engineering such as model-controller-view,
adapters, asynchronous processing etc..

Compare and contrast Android's approach to layouts and user interfaces in past platforms used: Layouts in Android can be related to 
layoutarrangements available in other platforms as well. Within the layout availability of views textboxes, buttons, check boxes, radio 
buttons,lists etc., and resources such as toolbar, menu bar is consistent with the widgets/objects used in UI designs; available on most 
platforms such as Java Swing, VB Forms, or web based UIs. However, one clear distinction of Android platform UI is that all the layout 
resource definitions and their properties are available in XML formatted files and hence the advantages of mark up language such as 
readability, and well formed are available. The hosting of a layout is abstracted in Android platform. Activities serve as the generic 
hosts for variety of layouts in Android platform.

Reflect on the `ArrayAdapter` and `ListView`used in pre-work: A list view is a list of several child views ( in this example several text
views ) that are available on the screen as an UI. These child view objects need to exist only when on screen. A list view can be 
enormous, and unnecessarily creating and storing view objects for an entire list could cause performance and memory problems. Adapter is 
used to create view objects only as they are needed. The list view asks an adapter for a view object when it needs to display a certain 
list item.   An adapter is thus a controller object that sits between the list view and the data set called array list containing the 
data that the list view should display.

Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`:  Within its implementation of getView(...), the 
adapter creates a view object from the correct item in the array list and returns that view object to the list view. The list view then 
adds the view object to itself as a child view, which gets the new view on screen. 

The definition of getView method that creates and returns a list item in the ArrayAdapter<T> class is as follows: 
public View getView(int position, View convertView, ViewGroup parent)
The covertView parameter is an existing list item that the adapter can reconfigure and return instead of creating a brand new object. 
Reusing view objects is a performance advantage thus not constantly creating and destroying the same type of object. A ListView can only 
display so many list items at a time, so there is no reason to have a number of unused view objects floating around and using up memory.
