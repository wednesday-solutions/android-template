git config user.email "william-the-bot@wednesday.is"
git config user.name "William The Bot"

git add $GITHUB_WORKSPACE/app/app.gradle
git commit -m "Bumped versionCode by 1 [skip ci]" # skip ci actually skips the ci

CURRENT_BRANCH=$(git branch --show-current)
git push origin $CURRENT_BRANCH
