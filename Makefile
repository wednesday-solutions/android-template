.PHONY: ktlintFormat ktlint lint lint-all

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