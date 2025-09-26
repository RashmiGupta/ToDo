
# ToDo
Simple Android Application: ToDo List
User can add and view a new task item to the list that is visible on main screen
User can click on a task in the list to edit the title text and persist the changes to the list
User can close the tasks that are done.  Closed tasks are removed from to do list, and list view is updated.
Application can persist todo items and retrieve them properly on app restart

# Technical 
data to persist in SQLite DB, edit dialog to add and update to do task item, and alert dialog when closing task item.
completion due dates for items displayed with expandable, and collapsable view, using spacing, and styling. using a custom adapter.

 
 # Walkthrough of all user stories:
 <img src='http://i.imgur.com/CUfAC9g.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
 GIF created with [LiceCap](http://www.cockos.com/licecap/)

 ## License

    Copyright 2017 Rashmi Gupta

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License. 
  
  ## Project Analysis
Android app development with XML, and Java. Project is structured with constructs of Android dev platform namely activity, services, fragments, and layout resources. 
These constructs can be related to software engineering patterns such as model-controller-view, adapters, asynchronous processing.

Layout development consists of UI components views textboxes, buttons, check boxes, radio buttons,lists etc., and navigation components
such as toolbar, menu bar is consistent with the widgets/objects used in UI designs. Most other UI platforms such as Java Swing, VB Forms, or web based UIs. 
provide packages to develop the views and navigation components. Resource definitions and their properties on Android dev platform are available in XML formatted files 
and hence the advantages of mark up language such as readability, and well formed are available. 

Activity serves as the generic hosts for variety of layouts in Android platform.

List view is a list of several child views ( in this example several text views ) that are available on the layout. These child view objects need to exist only when on screen. 
A list view can be enormous, and unnecessarily creating and storing view objects for an entire list could cause performance and memory problems. Adapter is used to create view objects 
only as they are needed. The list view asks an adapter for a view object when it needs to display a certain list item.   
An adapter is thus a controller object that sits between the list view and the data construct called array list containing the data that the list view should display.
Within its implementation of getView(...), the adapter creates a view object from the correct item in the array list and returns that view object to the list view. The list view then 
adds the view object to itself as a child view, which gets the new view on screen. The definition of getView method that creates and returns a list item in the ArrayAdapter<T> class is as follows: 
public View getView(int position, View convertView, ViewGroup parent) where covertView parameter is an existing list item that the adapter can reconfigure and return instead of creating a brand new object. 
Reusing view objects is a performance advantage thus not constantly creating and destroying the same type of object. 
A ListView can only display so many list items at a time, so there is no reason to have a number of unused view objects floating around and using up memory.
