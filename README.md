# vivy Java Code-CheckStyle

This plugin helps to reformat existing codebase to adopted CodeStyle and work in conjuction with other settings as specified in the steps below.

## Configuration steps

1. Add this to your ` build.gradle ` file : This is to fail build operation if there exists codestyles error(s). 
```
apply plugin: 'checkstyle'

    checkstyle {
        config project.resources.text.fromUri('https://raw.githubusercontent.com/oyewaleoyelami/check/master/checkstyle.xml')
        toolVersion = "6.0"
    }
    
``` 
    
2. The plugin requires [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) as a prerequisite, Install it, add and set the checkstyle to use the checkstyle file (same url as in step 1) via file via File | Other Settings | Checkstyle :: This is for real-time tacking of the coding style

3. Enable the Pluging under File | Editor | CodeStyle  and select VivyJava

## Building the Plugin

A  buildPlugin gradle operation will generate .jar file which can then be installed in IntelliJ via plugin "Installation from Disk"