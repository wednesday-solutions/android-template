CURRENT_BRANCH=$(git branch --show-current)

GRADLE_PATH=$GITHUB_WORKSPACE/gradlew
RELEASE_OUTPUT_FILE=app/build/outputs/apk/prod/release/

if [ "master" == "$CURRENT_BRANCH" ]; then
  echo "Current Branch is $CURRENT_BRANCH , proceeding with prod build"
  bash $GRADLE_PATH assembleProdRelease
else
  echo "Current Branch is NOT master, Proceeding with qa build"
  RELEASE_OUTPUT_FILE=app/build/outputs/apk/qa/release/
  bash $GRADLE_PATH assembleQaRelease
fi

echo "RELEASE_OUTPUT_FILE=$RELEASE_OUTPUT_FILE">>$GITHUB_ENV
