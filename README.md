# This is an automation project that will allow a user to get their IDE setup running with the following: 
•	Java
•	Maven
•	Selenium
•	TestNG
The user will be able to execute several test that will test the Hudl – Login feature with both positive and negative tests. 

The project is setup to navigate to https://www.hudl.com to begin executing the tests. This readme page will also explain to the user how to run other variations of the tests. 

There are a few things we need before running the tests. These are:
1.	Installation of Java.
a.	 Check if you have java installed by running the command below, if so you can move on to step 2. 
Note: Ensure your JAVA_HOME environment is the same of the location of the installed JDK
   $  java  -version
i.	Java installation can be found here or 
ii.	Java can be installed via www.brew.com with the following: 
brew install open jdk
2.	Setting up Eclipse IDE
a.	Installing TestNG plugin from Eclipse IDE
b.	Maven setup
3.	Executing Hudl Login Tests
a.	Running all tests
b.	Running only positive tests
c.	Running only negative tests


Setting up Eclipse
 
The Eclipse IDE can be downloaded here:
 Eclipse download page
Once you have installed Eclipse, TestNG will need to be added as a plugin. Follow the following steps to install TestNG to your Eclipse IDE.

TestNG Installation
1.	Launch Eclipse and click on the Help Menu once Eclipse is launched
2.	Select Install New Software
3.	Select Eclipse Marketplace
4.	Eclipse Marketplace window will launch. Via the Eclipse Marketplace pop up, type TestNG in the Find text field
 
5.	Click Go button
6.	Once results for TestNG search results window is launched, you will see TestNG for Eclipse in the results
 
7.	Click Install and Confirm Selected Features
 
8.	Accept the License and click on the Finish button

 

9.	Restart Eclipse
10.	TestNG is now installed as plugins to the Eclipse IDE

Setting up Maven
 
Maven can easily be downloaded here: Maven Installation Page

Add Maven to the your PATH. If maven is not installed, follow the following steps to install on a Mac OS

Maven to my PATH on MacOS. I added the following to the ~/.bash_profile.

1.	$ export PATH=/Users/admin/Documents/Software/apache-maven-3.5.0/bin:$PATH
Then sourced (execute) the content of the ~/.bash_profile
$ source ~/.bash_profile
•	Verify Maven was correctly installed Command:
Command:   $ mvn -version




Executing the Hudl – Login Tests

Clone the project into Eclipse validate no errors exists within the project, as the pom.xml file has all dependencies required to run the tests. 
You can also run the command below to check for outdated dependencies.
1.	Navigate to the following /src/test/java/resources
2.	Note the config.properties
3.	Open the file: 

 
The tests are set to run the “hudlWebsite” which can be modified if needed. To run the tests, you will need to fill out the following fields:
1.	validEmail – Set this to a valid email address for the hudlWebsite environment you are testing
2.	validPW – Set to valid pw for email address in step 1
3.	avatarInitials – Set this to the correct Avatar initials the user is testing
4.	invalidUser – set field to invalid user
5.	invalidCredentials – set to incorrect to invalidUser email
6.	invalidEmailFormat – Set to an incorrect email format, for example: “notAnEmail@address”
7.	#Correct Password just all uppercase with a valid email
8.	InvalidPassword =  (leave blank)
9.	emailRequiredMsg  -- Field to check for Email Required
10.	emptyEmail – Leave the email field required at login
11.	requiredMsg --  Please fill in all of the required fields
12.	noAcctText = Don't have an account?
The file also contains error messages the application will return. These are set in the following fields:
13.	invalidLoginMsg – Set to correct invalid login error message user will see “We don’t recognize that and/or password” via the Login Page
14.	emptyEmail – User to leave this field blank
15.	requiredMsg –Error message returned as “Please fill in all of the required fields”
16.	noAcctText – “Don’t have an account?”

Running your first Test
Running tests in Chrome need to build and compile tests
1.	Right Click the project, click Run As and select “Maven clean”
2.	Right click the project file, click Run As and Select “Maven install”
Navigate to the xml test file you want to run and right click, Run As  TestNG Suite
Once the test has finished, it has generated a .xml file with the results. 

You can now generate tests easily by editing the config.properties file with various scenarios to check Hudl’s Login security. 

