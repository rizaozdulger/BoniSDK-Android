BoniSDK-Android
===============

Boni Beacon SDK for Android devices.

Installation
-------
1. Add BoniBeaconSDK.jar file to your project.
2. Add following declarations to your AndroidManifest.xml file:

```xml
<uses-permission android:name="android.permission.BLUETOOTH"/>
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
```
```xml
<service android:name="me.boni.sdk.BoniBeaconService"
            android:exported="false"/>
<service android:name="me.boni.sdk.BoniMonitoringService"
            android:exported="false"/>
<receiver android:name="me.boni.sdk.BoniScheduleReceiver" >
        </receiver>
```

Usage
-------
Add following codes to your onCreate method.

```java
        BoniBeaconManager.getInstance(context).registerRangingListener(new BoniBeaconListener() {
            @Override
            public void onNearestRangedBeacon(BoniRegion boniRegion, BoniBeacon boniBeacon) {

            }

            @Override
            public void onRangedBeacons(BoniRegion boniRegion, final List<BoniBeacon> boniBeacons) {
                
            }
        });
```

Add following codes to your onStart method to start ranging beacons. You should use your Boni UUID.

```java
        BoniBeaconManager.getInstance(context).startRangingBeacons(new BoniRegion("test", BONI_UUID, null, null));
```

Add following codes to your onStop method to stop ranging beacons.

```java
        BoniBeaconManager.getInstance(context).stopRangingBeacons(region);
```
