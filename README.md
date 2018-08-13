# uvita custom checkstyle

## Configuration steps

1. Install the checkstyle-idea plugin in your IntelliJ IDE

2. Copy the config folder into your project root directory.

3. add plugin : 'checkstyle'  (This will use the checkstyle.xml file available in the config directory by default)
   checkstyle {
        toolVersion = "6.0"
    }
    
    to your build.gradle file : this will fail the build of the project if there are violations of the styles (Also, if the severity is set to error and not warning).
    
    ** specifying the version of the checkstyle is not mandatory (depends on the version of the checkstyle.xml file)
4. File | Other Settings | Checkstyle  to add and select the checkstyle.xml as the configuration file.

5. Import the checkstyle.xml scheme in the config folder into the IDE scheme (to enabke direct reformating of code)  via : File | Editor | Code Style | Import The Scheme

# Additional Resources

- http://checkstyle.sourceforge.net/writingchecks.html
- https://github.com/square/java-code-styles/blob/master/install.sh