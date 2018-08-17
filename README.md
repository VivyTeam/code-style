# Vivy Java Code-CheckStyle

This plugin helps to reformat existing codebase to adopted CodeStyle and work in conjunction with [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) and settings as follows:

## Configuration steps

1. Add this to your main ` build.gradle ` file : This should fail build operation if there exists codestyles adherence error(s).
```
apply plugin: 'checkstyle'

    checkstyle {
        config project.resources.text.fromUri('https://raw.githubusercontent.com/oyewaleoyelami/check/master/checkstyle.xml')
        toolVersion = "8.11"
    }
    
``` 
    
2. This plugin requires [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) as a prerequisit. Download and install it, add and set the checkstyle to be used, using the checkstyle file (url in previous stey) via file via File | Other Settings | Checkstyle :: This is for real-time tracking of the coding style.

3. Enable the Plugin : File | Editor | CodeStyle  and select VivyJava

## Building the Plugin

Presently, a buildPlugin gradle operation should generate .jar file which can then be added to IntelliJ as a plugin.