# Vivy Java Code-CheckStyle

This plugin helps to reformat existing codebase to adopted CodeStyle and work in conjunction with [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) and settings as follows:

## Configuration steps

1. Adapt your ` build.gradle ` file to the following structure : This should fail build operation if there exists codestyles adherence error(s).
```
   allprojects {
    apply plugin: 'checkstyle'

    checkstyle {
               config project.resources.text.fromUri('https://raw.githubusercontent.com/oyewaleoyelami/check/master/checkstyle.xml')
               toolVersion = "8.12"
           }

    repositories {
           jcenter()
           maven { url 'https://jitpack.io'}
       }

    dependencies {
               dependency 'com.puppycrawl.tools:checkstyle:8.12'
               dependency 'com.github.UvitaTeam.code-style:checkstyle-custom:enhance-SNAPSHOT'
         }

     }

   subprojects {

        dependencies {
            compile 'com.puppycrawl.tools:checkstyle'
            checkstyle 'com.github.UvitaTeam.code-style:checkstyle-custom'
          }
       }
    
``` 
    
2. This plugin requires [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) as a prerequisit. Download and install it, add and set the checkstyle to be used, using the checkstyle file (url in previous stey) via file via File | Other Settings | Checkstyle :: This is for real-time tracking of the coding style.

3. Enable the Plugin : File | Editor | CodeStyle  and select VivyJava

## Building the Plugin

Presently, a buildPlugin gradle operation should generate .jar file which can then be added to IntelliJ as a plugin.

## Resource

http://www.jetbrains.org/intellij/sdk/docs/welcome.html