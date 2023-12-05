# knock automate
Background:  This is one-day build to make a basic framework made using WebDriver, Java and TestNG to test Knock's visit schedule page.

# Setup Requirements:  
Designed to run chrome on mac. Additional environments can be supported by replacing the chromedriver in
the /other directory with chromedriver.exe for windows, or chromedriver made for linux, etc.

# Setup:
* Open project using 'import with existing sources'
* When  dialog prompts 'Import gradle project'? Do that import it suggests.
    If you get no dialog, try re-importing it, this time selecting the automation/build.gradle file, and 'open as project'
* The above step will download needed dependencies using gradle -wait until this is complete.
* Build project
* Ensure a recent version of Chrome is installed

# Running tests:
    In IDE, right click /src/test/resources/testNG.xml and choose RUN

# Improvements
How can this test be extended?
    Verify each step (asserts) in test performed by selenium have intended effect on UI, not just final result.
    Support windows, linux etc as options in config.
    Support firefox, safari, etc.
    Use remote machine for the browser by using saas client provider such as browserstack or setup on Aws.
    Robust logging needs to be added as standard out is just a crutch for now.
    Reporting using testNG.

How else could this feature be tested?
    Failure cases - leave missing data, and verify appointment cannot be saved.
    Enter symbols and unexpected data or length of data, verify performs according to expectation
    Setup client on different locale, and verify dates and times work correctly
    Same with other locale and also with keyboard/OS set to different language.
    Performance testing (multiple requests in short amount of time)
    Accessibilty testing (keying checking user input order, or integrating accessibility tools)
    Browser version testing and OS version testing
    Security testing (e.g. try sql injections)

What assumptions did you make when writing this test?
    It seems the time field and calendar will only show a time or month if it has availability:
    If its an actual framework I would not assume a given month would automatically have openings, and same for
    time, unless that was explicitly given in the specification, and even then would need error handling/ loggging.

How could this test be integrated into a CI/CD pipeline?
    Create a 'build' for tests which runs tests using testNG. (Make a trigger to start a build on commits to a
    pull request for relevant project). Given tests are made to be reliable, this build should be added to the
    workflow after commits and or unit tests such that it must also pass for the chain to get to final stages
     .i.e. publish/ upload artifact upload workflow. Send alerts based on pass or fail.
