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
feature/login
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
Review Pull Request
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

Pull requests should be reviewed before being merged into `develop`.

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
Add login functionality
Add login validation
Update project documentation
Prepare v1.2.0 release
```

Commits should be focused on a specific change rather than combining unrelated modifications.

---

## 6. Pull Requests

Pull requests are used to review changes before they are merged into `develop`.

A typical feature workflow is:

```text
feature/login
      |
      v
Create Pull Request
      |
      v
develop
```

Pull requests should include:

* A clear title
* A description of the changes
* The related issue
* Testing performed
* Any important implementation notes

Example:

```text
Title:
Add login functionality
```

Example description:

```text
## Changes

- Added login screen
- Added username validation
- Added password validation
- Prevented access with invalid credentials
- Opened MainFrame after successful login

## Testing

- Tested valid credentials
- Tested invalid username
- Tested invalid password
- Tested empty fields

Closes #XX
```

Pull requests should be reviewed before merging.

---

## 7. Release Preparation

Before releasing a new version:

1. Ensure all intended changes are merged into `develop`.
2. Build the project successfully.
3. Test the application.
4. Test MySQL connectivity.
5. Verify database operations.
6. Test login functionality.
7. Perform integration testing.
8. Update the README.
9. Update project documentation.
10. Update the project version.
11. Commit the release changes.
12. Push `develop`.
13. Merge `develop` into `main`.
14. Create a version tag.
15. Push the tag.
16. Create the GitHub release.

---

## 8. Versioning

SmartPark follows semantic-style versioning.

The releases are:

```text
v1.0.0
v1.1.0
v1.2.0
```

Future versions can follow the same versioning convention:

```text
v1.2.1
v1.3.0
v2.0.0
```

### Version Guidelines

* **Major version** (`2.0.0`) — Significant changes or incompatible changes.
* **Minor version** (`1.2.0`) — New features that maintain compatibility.
* **Patch version** (`1.2.1`) — Bug fixes and small improvements.

---

## 9. Version 1.0.0 Release

Version `1.0.0` represented the first stable implementation of SmartPark.

The release established the core:

* Java Swing interface
* Domain models
* In-memory repositories
* Services
* Controllers
* Parking management
* Vehicle management
* Parking sessions
* Analytics

---

## 10. Version 1.1.0 Release

Version `1.1.0` introduced:

* MySQL database integration
* Persistent application storage
* MySQL repository implementations
* Database connection configuration
* UI consistency improvements
* Bug fixes
* Improved occupied parking-space presentation
* Updated documentation

The release was created after the `develop` branch was tested and prepared for production.

---

## 11. Version 1.2.0 Release

Version `1.2.0` introduces the application login system.

The main changes are:

* Added administrator login screen
* Added username and password validation
* Prevented access using invalid credentials
* Added authentication before opening the main application
* Updated documentation
* Updated testing procedures

The configured administrator credentials are:

```text
Username: Admin
Password: admin05
```

The release should only be created after the login functionality and existing SmartPark functionality have been tested.

---

## 12. Release Tag

The `v1.2.0` release tag should be:

```text
v1.2.0
```

The tag should point to the final stable commit on `main`.

---

## 13. GitHub Release

The GitHub release should use:

```text
v1.2.0
```

as the release tag and should include the final release notes.

The release notes should identify the main changes introduced since `v1.1.0`.

Example:

```text
# SmartPark v1.2.0

## What's New

- Added administrator login
- Added login validation
- Added authentication before application access
- Improved application startup flow
- Updated documentation
- Updated testing documentation

## Previous Release

v1.1.0
```

---

## 14. Post-Release Development

After `v1.2.0` is released, future development should continue on `develop` or appropriate feature/fix branches.

`main` should remain stable until the next release.

Future features should follow the same workflow:

```text
develop
   |
   v
feature branch
   |
   v
Development
   |
   v
Testing
   |
   v
Pull Request
   |
   v
develop
   |
   v
Release Testing
   |
   v
main
   |
   v
Version Tag
   |
   v
GitHub Release
```

---

## 15. Git Workflow Summary

The SmartPark Git workflow can be summarized as:

```text
develop
   |
   +----> feature/fix branch
   |              |
   |              v
   |         Development
   |              |
   |              v
   |            Testing
   |              |
   |              v
   |       Pull Request
   |              |
   |              v
   +--------> develop
                  |
                  v
           Release Testing
                  |
                  v
                main
                  |
                  v
             Version Tag
                  |
                  v
           GitHub Release
```

This workflow provides a clear separation between active development and stable releases while allowing features and fixes to be reviewed and tested before integration.
