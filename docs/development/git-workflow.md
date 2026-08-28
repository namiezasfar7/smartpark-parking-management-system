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
feature/mysql-integration
```

Bug fixes can use:

```text
fix/validation-error
fix/session-completion
fix/ui-inconsistency
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

Changes should be tested before creating a pull request.

Pull requests are reviewed before being merged into `develop`.

---

## 5. Commits

Commits should clearly describe the change being introduced.

Examples:

```text
Add MySQL repository integration
Add database connection configuration
Fix vehicle registration validation
Fix parking UI inconsistency
Improve occupied parking display
Fix parking session completion
Update project documentation
Prepare v1.1.0 release
```

Commits should be focused on a specific change rather than combining unrelated modifications.

---

## 6. Release Preparation

Before releasing a new version:

1. Ensure all intended changes are merged into `develop`.
2. Build the project successfully.
3. Test the application.
4. Test MySQL connectivity.
5. Verify database operations.
6. Perform integration testing.
7. Update the README.
8. Update project documentation.
9. Update the project version.
10. Commit the release changes.
11. Push `develop`.
12. Merge `develop` into `main`.
13. Create a version tag.
14. Push the tag.
15. Create the GitHub release.

---

## 7. Versioning

SmartPark follows semantic-style versioning.

The releases are:

```text
v1.0.0
v1.1.0
```

Future versions can follow the same versioning convention:

```text
v1.1.1
v1.2.0
v2.0.0
```

### Version Guidelines

* **Major version** (`2.0.0`) — Significant changes or incompatible changes.
* **Minor version** (`1.1.0`) — New features that maintain compatibility.
* **Patch version** (`1.1.1`) — Bug fixes and small improvements.

---

## 8. Version 1.1.0 Release

Version `1.1.0` introduces:

* MySQL database integration
* Persistent application storage
* MySQL repository implementations
* Database connection configuration
* UI consistency improvements
* Bug fixes
* Improved occupied parking-space presentation
* Updated documentation

The release should be created only after the `develop` branch has been fully tested.

---

## 9. Release Tag

The v1.1.0 release tag should be:

```text
v1.1.0
```

The tag should point to the final stable commit on `main`.

---

## 10. GitHub Release

The GitHub release should use:

```text
v1.1.0
```

as the release tag and should include the final release notes.

The release should identify the major changes introduced since `v1.0.0`.

---

## 11. Post-Release Development

After `v1.1.0` is released, future development should continue on `develop` or appropriate feature/fix branches.

`main` should remain stable until the next release.