#!/bin/bash

echo "Installing Vivy IntelliJ configs..."

CONFIGS="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )/config"

for i in $HOME/Library/Preferences/IntelliJIdea*  \
         $HOME/Library/Preferences/IdeaIC*        \
         $HOME/.IntelliJIdea*/config              \
         $HOME/.IdeaIC*/config                    
       
do
  if [[ -d $i ]]; then
    # Install codestyles
    mkdir -p $i/codestyles
    cp -frv "$CONFIGS/codestyles"/* $i/codestyles
  fi
  
done

echo "Done."

echo ""
echo "Restart IntelliJ , go to preferences, and apply 'VivyJava'"
