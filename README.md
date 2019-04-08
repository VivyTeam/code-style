# How to enable Checkstyle in your project

Add checkstyle config from [Checkstyle XML File](/vivy-idea-plugin/src/main/resources/META-INF/file/checkstyle.xml)


## Configure build.gradle

Adapt your ` build.gradle ` file to the following structure : This should fail build operation if there exists codestyles adherence error(s).
```
   allprojects {
      apply plugin: 'checkstyle'

      checkstyle {
         config project.resources.text.fromUri('https://raw.githubusercontent.com/VivyTeam/code-style/master/vivy-idea-plugin/src/main/resources/META-INF/file/checkstyle.xml')
         
         // To disable in tests. We generally appreciate tests aligned with checkstyle, 
         // but that's not mandatory in some cases
         sourceSets = [project.sourceSets.main] 
      }

      repositories {
           jcenter()
           maven { url 'https://jitpack.io'}
      }

      dependencyManagement {
         dependencies {
               dependency 'com.github.VivyTeam.code-style:checkstyle-custom:1.1.0'
         }
      }
   }
   // -------

   subprojects {
        dependencies {
            checkstyle 'com.github.VivyTeam.code-style:checkstyle-custom'
        }
   }
    
``` 

## Adjust Idea

1. First of all you need to install `CheckStyle-IDEA Plugin`. 

2. Secondly you need to download latest release of vivy checks from here: 
[latest release](https://github.com/VivyTeam/code-style/releases/latest). 

3. Now you need to add vivy-checkstyle to idea. 
* Preferences-> Other Settings -> Checkstyle 
  * **Third PartyChecks -> + -> /path/to/custom-checkstyle.jar**
  * **Configuration -> + -> link_from_above_to_checkstyle_config**
 
Or as a screenshot: 

![Checkstyle Idea Configuration](docs/CheckstyleConfigurationIdea.png?raw=true "Checkstyle Configuration")
