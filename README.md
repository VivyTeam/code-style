# uvita custom checkstyle

## Configuration steps

1. Clone the repository and run the install.sh file.

2. Add this to your ` build.gradle ` file
```
apply plugin: 'checkstyle'

    checkstyle {
        config project.resources.text.fromUri('https://raw.githubusercontent.com/oyewaleoyelami/check/master/checkstyle.xml')
        toolVersion = "6.0"
    }
    
``` 
    
3. Install [CheckStyle-Idea Plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea)  and set the checkstyle to use file via File | Other Settings | Checkstyle

