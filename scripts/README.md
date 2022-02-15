## Scripts Setup

#### Git Hooks
- Run the following command to make the `setup-hooks` script executable
```console
chmod +x ./scripts/setup-hooks.sh
```
- Then run the `setup-hooks` script. This will setup the git hooks.
```console
./scripts/setup-hooks.sh
```

## Scripts Details

#### Hooks (`/scripts/hooks/`)
A [git hook](https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks) is specified before the `push` event which checks if the linting is done correctly
If the lint check fails, a correction attempt is made using the `./gradlew ktLintFormat` task


#### Actions(`/scripts/actions/`)
This contains all the scripts that are used at the various steps inside the [Github Actions CI/CD Pipeline](.github/workflows/)

- ```build_file_according_to_flavour.sh```:
This builds the `prod` flavour by default on the `master/main` branch and the `qa` flavour by default

- `commit_and_update.sh`:
This commits all changes made by the various scripts back into version control.

> `Note:` the usage of [skip ci] in the commit message which prevents re-triggering the pipeline here. For more info read [here](https://docs.github.com/en/actions/managing-workflow-runs/skipping-workflow-runs)

- `generate_new_version_code.sh`:
This creates a tag with the current version code and then updates it by one for the future releases


