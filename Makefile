.PHONY: ktlintFormat ktlint lint lint-all setup-hooks

# Lint

ktlintFormat:
	./gradlew ktlintFormat

ktlint:
	./gradlew ktlint

lint:
	./gradlew lint

lint-all: lint ktlintFormat

setup-hooks:
	./scripts/setup-hooks.sh