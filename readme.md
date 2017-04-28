# Github Updater for Java
This is a small utility library I've written to check if there is a newer release on the application's github repo. It has several options to just check if there is a newer release, but you can also download this new release if you want to do so. It's still early in development but it provides some easy utilities to interact with your github releases.

## How to implement this lib.
To use this library you have to initialize the GithubUtility class.
The constructor takes three parameters:
* The name of the repo owner.
* The name of the repo.
* The tag name of the applications current release.

For example for this repo it will like this:
```java
GithubUtility githubUtility = new GithubUtility("eternia16", "javaGithubUpdater", "v0.1alpha");
```
You can get these parameters from the URL of your project:
https://github.com/eternia16/javaGithubUpdater/

The tag name can be retrieved from your projects releases page.


After that you can you can use the lib is ready to go.

Take caution that this lib is not yet multithreaded and will block your application if you don't do it yourself.
