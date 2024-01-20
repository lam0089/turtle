# turtle
Turtle Graphics program in Java
================================

You need to create a turtle with a name before moving the turtle. There are two options to run turtle graphics:<br>
(1) Type the commands in the textfield and press Enter. You can use the following language commands to control the turtles.<br>
(2) Click 'Search' to select a local file which contains a list of command. Then click 'Execute' to run the commands.<br>

**Language commands**<br>
turtle name - create a new turtle identified by the given name<br>
move name x - moves the named turtle forward by x units<br>
left name x - rotate the turtle anticlockwise by x degrees<br>
right name x - rotate the turtle clockwise by x degrees<br>
pen name up - lift the pen off the “paper”<br>
pen name down - put the pen down so that subsequent moves draw on the screen<br>
colour name c - set the drawing colour of the turtle appropriately<br>

**An example**<br>
turtle ann<br>
move ann 50<br>
left ann 90<br>
move ann 60<br>
left ann 90<br>
colour ann green<br>
move ann 70<br>
left ann 90<br>
move ann 80<br>
turtle pete<br>
right pete 90<br>
move pete 60<br>
right pete 90<br>
move pete 70<br>
pen pete up<br>
move pete 70<br>
pen pete down<br>
colour pete red<br>
move pete 40<br>


The program has been tested in the Eclipse Version: 2020-03 (4.15.0) and Visual Studio Code Version: 1.85.1.<br>
Make sure Java version should be at least "13.0.2".<br>
