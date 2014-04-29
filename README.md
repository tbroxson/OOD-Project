OOD-Project
===========

Project written by Jose Perea and Tim Broxson


The tool to create this project was Eclipse. The tester has two options on how to run this
this project. For the first, the tester may simply open the project on Eclipse, because what
is being delivered is a zipped folder with the entire project. The second option is to extract
the files from the zipped folders, locate the source code, and copy and paste all the source 
into a new Java project, compile it, and test it from there.

The project consists of two packages, a model and a view. The model package contains all the
source for classes that behave like models in the MVC relationship. The view package contains
all the source for the classes that are views and controllers for the models. Inside the model
package there is the ShoppinCart.java file that contains the driver for the entire project.

Once the tester has either opened the project in Eclipse or recompiled it into a new project,
all they have to do is run the project. The pertinent data is serialized (user database, items
in the inventory, profit numbers) so the application is ready to go. The tester can click on
the create user button to create a user for themselves that they would want to make.

There are two types of users that can be created, a buyer or a seller. The tester can make a
user for both to test all the interfaces for the application. If the tester is a buyer type, then
he/she will be taken to the current buyer's inventory where they can select mulitple items
to purchase. Once the seller selects the items, then they will click on checkout and be taken
to their cart where they can select the appropriate number of items they want to select. The
application will make sure that the enter selects the correct available amount. Then the user
will click on checkout where they will be shown the total amount of the products that have
been purchased. If the total is correct then they click on submit, and the transaction goes through.
If not, then close the application and restart it.

If the user chooses to be a seller, then he is taken to the current inventory screen where
an item can be added, updated, or deleted. The seller can also check out the current profit
by click on the profit the button. What is displayed is the total revenue that has been made
and the cost is of the current items in the inventory.

The application can only be exited when you hit the top x at the top. 
