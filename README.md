# CS Games 2016 - Sample Android App

This project will guide you setting up your environment to build and run a sample Android application on your local machine.

## Prerequisites

Make sure you have the following software installed before beginning:

- Latest version of Android Studio (1.5.1)
- Recent version of the Android SDK (at least API 21)

You can download these from the [Android Developer website](http://developer.android.com/sdk/index.html).

> **NOTE:** If you have a Mac computer running OS X 10.10 or later, you may also be interested in our [Sample iOS app](https://github.com/mirego/csgames16-sample-ios/), which uses Xcode and the latest iOS SDK.

## Getting started

First, clone the project from Github:

```
git clone git@github.com:mirego/csgames16-sample-android.git
```

Then, in Android Studio: 

- Select **Import project (Eclipse, ADT, Gradle, etc.)** in the Welcome Screen, go find the repository you just cloned, and click **OK**.
- Once the project is open, click on **Sync Project with Gradle Files** in the main toolbar (or navigate to `Tools -> Android` in the application menu and select the same option).

<p align="center"><img src="https://cloud.githubusercontent.com/assets/4378424/13450169/9f925920-e000-11e5-999a-464b9949ee9a.png" width="199"></p>

Once you see a `BUILD SUCCESSFUL` notice in the Gradle Console, your environment should be ready to build and run the project.

## Building the project

The project should have already been configured as an Android project in Android Studio, therefore you should see a target named `app` in the main toolbar, with **Play** and **Debug** buttons on its right.

Press on the **Debug** icon, and if you don't already have one, [create a new Android Virtual Device](http://developer.android.com/tools/devices/managing-avds.html), then select it to run the project.

Once the app appears running in your Virtual Device, your environment is ready for the competition.

## License

This sample app is © 2016 [Mirego](http://www.mirego.com) and may be freely
distributed under the [New BSD license](http://opensource.org/licenses/BSD-3-Clause).
See the [`LICENSE.md`](https://github.com/mirego/csgames16-sample-ios/blob/master/LICENSE.md) file.

## About Mirego

[Mirego](http://mirego.com) is a team of passionate people who believe that work is a place where you can innovate and have fun. We're a team of [talented people](http://life.mirego.com) who imagine and build beautiful Web and mobile applications. We come together to share ideas and [change the world](http://mirego.org).

We also [love open-source software](http://open.mirego.com) and we try to give back to the community as much as we can.
