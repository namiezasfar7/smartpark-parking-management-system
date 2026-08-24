# SmartPark Git Workflow

## 1. Overview

SmartPark uses Git for version control and GitHub for repository hosting and collaboration.

The project follows a feature-branch workflow to keep development organized and maintain a stable release branch.

---

## 2. Main Branches

### `main`

`main` contains stable released versions of the application.

No unfinished development should be committed directly to `main`.

### `develop`

`develop` is used for active development and integration.

Completed features are merged into `develop` before being considered for release.

---

## 3. Feature Branches

New functionality should be developed on a separate branch created from `develop`.

Examples:

```text
feature/vehicle-management
feature/parking-management
feature/session-management
feature/analytics
```

Bug fixes can use:

```text
fix/validation-error
fix/session-completion
```

Exception-related work can use:

```text
exception/validation
```

After the work is completed and tested, the branch is merged into `develop`.

Feature and temporary work branches should be deleted after successful merging.

---

## 4. Development Workflow

The general workflow is:

```text
Create branch
     |
     v
Develop feature
     |
     v
Compile and test
     |
     v
Commit changes
     |
     v
Push branch
     |
     v
Create Pull Request
     |
     v
Merge into develop
     |
     v
Integration testing
     |
     v
Release preparation
     |
     v
Merge develop -> main
     |
     v
Create version tag
     |
     v
Create GitHub release
```

Development branches should be created from the latest version of `develop`.

Changes should be tested before creating a pull request. Pull requests are reviewed before being merged into `develop`.

---

## 5. Commits

Commits should clearly describe the change being introduced.

Examples:

```text
Add vehicle validation
Add parking session management
Implement analytics service
Fix vehicle registration validation
Add session completion handling
Update project documentation
Prepare v1.0.0 release
```

Commits should be focused on a specific change rather than combining unrelated modifications.

---

## 6. Release Workflow

Before releasing a new version:

1. Ensure all intended changes are merged into `develop`.
2. Build the project successfully.
3. Test the application.
4. Perform integration testing.
5. Update the README.
6. Update project documentation.
7. Update the project version.
8. Commit the release changes.
9. Merge `develop` into `main`.
10. Create a version tag.
11. Create the GitHub release.

The `main` branch should only contain code that is considered stable and ready for release.

---

## 7. Versioning

SmartPark follows semantic-style versioning.

The first stable release is:

```text
v1.0.0
```

Future versions can follow the same versioning convention:

```text
v1.0.1
v1.1.0
v2.0.0
```

The version number should reflect the type and significance of changes introduced.

### Version Guidelines

* **Major version** (`2.0.0`) — Significant changes or incompatible changes.
* **Minor version** (`1.1.0`) — New features that maintain compatibility.
* **Patch version** (`1.0.1`) — Bug fixes and small improvements.
