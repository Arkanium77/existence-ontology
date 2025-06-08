# Existence Ontology: solving "existential" questions

**[Русская версия](readme.ru.md)**

## About the project

Sometimes I look at the code and wonder: does this object even exist? And what does it mean for it to "exist"? If a variable is `null`, its value is meaningless. If it's an `Optional`, you can ask `o.isPresent()` and get an answer. But what if inside that `Optional` there is yet another `Optional`? What about a string that contains only spaces? The method `"     ".isEmpty()` thinks it is not empty, but there's zero useful information in it! The same happens with many business objects that can be "empty" when required fields in a form are left blank.

Existence Ontology aims to solve these problems by creating a system of rules and convenient interfaces to use them. Any constructive suggestions stated in a friendly manner are welcome.

## Components

- `core`
    - **Description**: A basic library written in Java 8 without third-party dependencies (only for testing). It allows you to create an `ExistenceChecker` capable of performing all object tests we find interesting. It contains a ready-to-use checker implementation and some useful rules.
    - **Status**: `IN PROGRESS`
- `lombok-utils`
    - **Description**: A library with the `ExistenceUtils` class that lets you perform existence checks without creating an instance of `ExistenceChecker`. It also offers an `@ExtensionMethod` (thanks Lombok!) for the `Object` class.
    - **Status**: `CONSIDERING`
    - **Reasoning**: `ExistenceUtils` would have to contain a static field with an `ExistenceChecker` instance. But what's the use without being able to add your own rules? Allowing modification of what is essentially a global variable from anywhere is unsafe. Still, it's quite convenient...
- `spring-starter`
    - **Description**: A standard starter that provides preconfigured `ExistenceChecker` beans, along with the ability to scan and automatically register rules by package and name list. Spring Boot 3, Java 21.
    - **Status**: `TODO`

---

# Branching and Versioning Rules

The project uses manual versioning based on [**SemVer (X.Y.Z)**](https://semver.org/):

- **X** — major: breaking changes
- **Y** — minor: new features without breaking backward compatibility
- **Z** — patch: bug fixes and documentation updates without changing the contract

## Main branches

- **`master`** — contains only **stable published versions**. Commits land in `master` either:
    - through merging from `rc/*`, or
    - through merging from `fix/*`.

- **`rc/module-x.y.0`** — release candidate branch. Created from `master`, it collects new features for a specific module. The version is updated **only here**. After stabilization and final testing it is merged into `master` and tagged.

- **`feature/*`** — working branches for new features. They branch from `rc/module-x.y.0`. The version is left untouched. When work is finished, a Merge Request to the corresponding `rc/module-x.y.0` is created.

- **`fix/module-x.y.z-*`** — quick bug fixes. They branch from `master` and bump the Z part of the version. The text after the dash describes the changes, for example: `fix/core-1.5.0-nullpointer_in_static_beans`. After completion, a Merge Request into `master` is created and the version is bumped manually.

## Example release process

1. Right after merging the previous `core` release, a new branch `rc/core-1.5.0` is created from `master`.
2. All features are developed in `feature/*` and merged into `rc/core-1.5.0` via Merge Request.
3. The version is set and updated manually **in the `rc/1.5.0` branch** once, closer to the end. If necessary (for example, in the case of an urgent breaking change), the version number and branch name can be adjusted.
4. When the branch is ready:
    - nothing else is merged into it,
    - it is merged into `master`,
    - the commit is tagged as `core-v1.5.0`,
    - publication (if any) happens by tag.

## Example hotfix process

1. A branch `fix/core-1.5.1-some_bug` is created from `master`.
2. The fix is applied.
3. The version is increased to `1.5.1`.
4. The branch is merged into `master`, and a tag `core-v1.5.1` is created.

## Common rules

- **Versions must not be touched in `feature/*` branches**.
- Versions are changed **only in `rc/*` and `fix/*` branches**.
- Publication happens **only from `master` by tag**, either manual or through CI.
- Every tag `module-vX.Y.Z` points to a commit with the corresponding version in the code.
- Every Merge Request should include a description of changes in Russian or English markdown.
- When merging, Fast Forward is used. Commits are squashed, the headline becomes the feature branch name, and the body contains the Merge Request description.