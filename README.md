BoniSDK-Android
===============

Boni Beacon SDK for Android devices.

Installation
-------
1. Add boni_beacon_sdk_v0_1_2.jar file to your project.
2. Add following declarations to your AndroidManifest.xml file:

```xml
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
```
```xml
<service android:name="me.boni.sdk.BoniBeaconService"
            android:exported="false"/>
```

Usage
-------
Add following codes to your onCreate method.

```java
        boniBeaconManager = new BoniBeaconManager(context, new BoniBeaconListener() {
            @Override
            public void onNearestRangedBeacon(BoniBeacon boniBeacon) {
                //neares beacon in region
            }

            @Override
            public void onRangedBeacons(final List<BoniBeacon> boniBeacons) {
                //ranged beacons in region
            }
        });
```

Add following codes to your onStart method to start ranging beacons. You should use your Boni UUID.

```java
        boniBeaconManager.startRanging(YOUR_BONI_UUID);
```

Add following codes to your onStop method to stop ranging beacons.

```java
        boniBeaconManager.stopRanging();
```
