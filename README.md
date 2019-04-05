# Plugin does not work now
Plugin is outdated and can't be used with a new Idea. 
Checkstyle file with custom checks that we have can't be used in plugin either.
So the main file in this repository is [checkstyle.xml](vivy-idea-plugin/src/main/resources/META-INF/file/checkstyle.xml) 
which you can use in gradle checkstyle configuration.

# Vivy Java Code-CheckStyle

This plugin helps to reformat existing codebase to adopted CodeStyle and work in conjunction with [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) and settings as follows:

## Configuration steps

1. Adapt your ` build.gradle ` file to the following structure : This should fail build operation if there exists codestyles adherence error(s).
```
   allprojects {
    apply plugin: 'checkstyle'

    checkstyle {
               config project.resources.text.fromUri('https://raw.githubusercontent.com/UvitaTeam/code-style/master/vivy-idea-plugin/src/main/resources/META-INF/file/checkstyle.xml')
           }

    repositories {
           jcenter()
           maven { url 'https://jitpack.io'}
       }

    dependencies {
               dependency 'com.github.UvitaTeam.code-style:checkstyle-custom:1.0.0'
         }

     }

   subprojects {

        dependencies {
            checkstyle 'com.github.UvitaTeam.code-style:checkstyle-custom'
          }
       }
    
``` 
    
2. This plugin requires [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) as a prerequisite. Download and install it, add and set the checkstyle to be used, using the checkstyle file (url in previous step) via file via File | Other Settings | Checkstyle :: This is for real-time tracking of the coding style possible violation(s).

3. Enable the Plugin : File | Editor | CodeStyle  and select Checker

## Building the Plugin

A buildPlugin gradle operation should generate .jar file which can then be added to IntelliJ as a plugin.

## Resource

http://www.jetbrains.org/intellij/sdk/docs/welcome.html