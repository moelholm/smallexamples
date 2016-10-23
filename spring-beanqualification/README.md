# Spring : Bean Qualification

This example shows different techniques to qualifying competing beans.

It is comprised of a number of modules:
- `00_TheProblem` shows the problem if you do not attempt to qualify the injection
- `0x_TheSolution_XXX` these modules show different solutions, - ie: how you can solve the issue

### Building and running the modules

Here's how you build an run a specific module:

     ./gradlew 02_TheSolution_ByMemberName:test
     
Notice the module name.