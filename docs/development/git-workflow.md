# Git & GitHub Workflow

## Branches

### main

The `main` branch contains stable versions of the SmartPark system.

Direct development on `main` is not permitted.

### develop

The `develop` branch contains the current integrated development version.

Features are merged into `develop` through pull requests.

### Feature branches

Individual features are developed using feature branches.

Naming convention:

`feature/<feature-name>`

Examples:

- `feature/vehicle-management`
- `feature/parking-management`
- `feature/dashboard-ui`
- `feature/report-generation`

## Development Workflow

1. Update the local `develop` branch.
2. Create a feature branch.
3. Implement the assigned feature.
4. Test the feature locally.
5. Commit the changes.
6. Push the feature branch.
7. Create a pull request.
8. Have another team member review the changes.
9. Merge into `develop`.
10. Test the integrated system.

## Commit Convention

Commit messages should clearly describe the change.

Examples:

- `Create Vehicle model`
- `Implement parking spot service`
- `Create dashboard UI`
- `Add vehicle validation`
- `Fix parking availability calculation`

Avoid vague commit messages such as:

- `update`
- `changes`
- `final`
- `stuff`

## Stable Releases

When the integrated `develop` branch is tested and considered stable, it can be merged into `main`.

## Important Rules

- Do not directly push development work to `main`.
- Do not force-push shared branches.
- Pull the latest `develop` before starting new work.
- Keep commits focused on one logical change.
- Review pull requests before merging.