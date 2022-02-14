currentVersionCode=$(awk '/versionCode/ {print $2}' $GITHUB_WORKSPACE/app/app.gradle | head -1)

# Making changes to files permanent using sed
let "currentVersionCode=currentVersionCode+1"
sed -i 's/versionCode [0-9a-zA-Z -_]*/versionCode '$currentVersionCode'/' $GITHUB_WORKSPACE/app/app.gradle

# Adding those permanent changes back to version control

git config user.email "william-the-bot@wednesday.is"
git config user.name "William The Bot"

git add $GITHUB_WORKSPACE/app/app.gradle
git commit -m "Bumped versionCode by 1 [skip ci]" # skip ci actually skips the ci

CURRENT_BRANCH=$(git branch --show-current)
git push origin $CURRENT_BRANCH
