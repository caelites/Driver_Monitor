/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.re.eslab.konanalysis.Model.Services.BleServices;

import java.util.HashMap;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class GattAttributes {
    private static HashMap<String, String> attributes = new HashMap();

    //Vlogger
    public static String VLOGGER_DEVELOPER = "6e400001-b5a3-f393-e0a9-e50e24dcca9e";
    public static String VLOGGER_DEVELOPER_RX = "6e400002-b5a3-f393-e0a9-e50e24dcca9e";
    public static String VLOGGER_DEVELOPER_TX = "6e400003-b5a3-f393-e0a9-e50e24dcca9e";
    public static String VLOGGER_DEVELOPER_CLIENT = "00002902-0000-1000-8000-00805f9b34fb";

    static {
        attributes.put("00001800-0000-1000-8000-00805f9b34fb", "Generic Access");
        attributes.put("00001801-0000-1000-8000-00805f9b34fb", "Generic Attribute");
        attributes.put("00001802-0000-1000-8000-00805f9b34fb", "Immediate Alert");
        attributes.put("00001803-0000-1000-8000-00805f9b34fb", "Link Loss");
        attributes.put("00001804-0000-1000-8000-00805f9b34fb", "Tx Power");
        attributes.put("00001805-0000-1000-8000-00805f9b34fb", "Current Time Service");
        attributes.put("00001806-0000-1000-8000-00805f9b34fb", "Reference Time Update Service");
        attributes.put("00001807-0000-1000-8000-00805f9b34fb", "Next DST Change Service");
        attributes.put("00001808-0000-1000-8000-00805f9b34fb", "Glucose");
        attributes.put("00001809-0000-1000-8000-00805f9b34fb", "Health Thermometer");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information");
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate");
        attributes.put("0000180e-0000-1000-8000-00805f9b34fb", "Phone Alert Status Service");
        attributes.put("0000180f-0000-1000-8000-00805f9b34fb", "Battery Service");
        attributes.put("00001810-0000-1000-8000-00805f9b34fb", "Blood Pressure");
        attributes.put("00001811-0000-1000-8000-00805f9b34fb", "Alert Notification Service");
        attributes.put("00001812-0000-1000-8000-00805f9b34fb", "Human Interface Device");
        attributes.put("00001813-0000-1000-8000-00805f9b34fb", "Scan Parameters");
        attributes.put("00001814-0000-1000-8000-00805f9b34fb", "Running Speed and Cadence");
        attributes.put("00001815-0000-1000-8000-00805f9b34fb", "Automation IO");
        attributes.put("00001816-0000-1000-8000-00805f9b34fb", "Cycling Speed and Cadence");
        attributes.put("00001818-0000-1000-8000-00805f9b34fb", "Cycling Power");
        attributes.put("00001819-0000-1000-8000-00805f9b34fb", "Location and Navigation");
        attributes.put("0000181a-0000-1000-8000-00805f9b34fb", "Environmental Sensing");
        attributes.put("0000181b-0000-1000-8000-00805f9b34fb", "Body Composition");
        attributes.put("0000181c-0000-1000-8000-00805f9b34fb", "User Data");
        attributes.put("0000181d-0000-1000-8000-00805f9b34fb", "Weight Scale");
        attributes.put("0000181e-0000-1000-8000-00805f9b34fb", "Bond Management Service");
        attributes.put("0000181f-0000-1000-8000-00805f9b34fb", "Continuous Glucose Monitoring");
        attributes.put("00001820-0000-1000-8000-00805f9b34fb", "Internet Protocol Support Service");
        attributes.put("00001821-0000-1000-8000-00805f9b34fb", "Indoor Positioning");
        attributes.put("00001822-0000-1000-8000-00805f9b34fb", "Pulse Oximeter Service");
        attributes.put("00001823-0000-1000-8000-00805f9b34fb", "HTTP Proxy");
        attributes.put("00001824-0000-1000-8000-00805f9b34fb", "Transport Discovery");
        attributes.put("00001825-0000-1000-8000-00805f9b34fb", "Object Transfer Service");
        attributes.put("00001826-0000-1000-8000-00805f9b34fb", "Fitness Machine");
        attributes.put("00001827-0000-1000-8000-00805f9b34fb", "Mesh Provisioning Service");
        attributes.put("00001828-0000-1000-8000-00805f9b34fb", "Mesh Proxy Service");
        attributes.put("00001829-0000-1000-8000-00805f9b34fb", "Reconnection Configuration");
        attributes.put("00002a00-0000-1000-8000-00805f9b34fb", "Device Name");
        attributes.put("00002a01-0000-1000-8000-00805f9b34fb", "Appearance");
        attributes.put("00002a02-0000-1000-8000-00805f9b34fb", "Peripheral Privacy Flag");
        attributes.put("00002a03-0000-1000-8000-00805f9b34fb", "Reconnection Address");
        attributes.put("00002a04-0000-1000-8000-00805f9b34fb", "Peripheral Preferred Connection Parameters");
        attributes.put("00002a05-0000-1000-8000-00805f9b34fb", "Service Changed");
        attributes.put("00002a06-0000-1000-8000-00805f9b34fb", "Alert Level");
        attributes.put("00002a07-0000-1000-8000-00805f9b34fb", "Tx Power Level");
        attributes.put("00002a08-0000-1000-8000-00805f9b34fb", "Date Time");
        attributes.put("00002a09-0000-1000-8000-00805f9b34fb", "Day of Week");
        attributes.put("00002a0a-0000-1000-8000-00805f9b34fb", "Day Date Time");
        attributes.put("00002a0b-0000-1000-8000-00805f9b34fb", "Exact Time 100");
        attributes.put("00002a0c-0000-1000-8000-00805f9b34fb", "Exact Time 256");
        attributes.put("00002a0d-0000-1000-8000-00805f9b34fb", "DST Offset");
        attributes.put("00002a0e-0000-1000-8000-00805f9b34fb", "Time Zone");
        attributes.put("00002a0f-0000-1000-8000-00805f9b34fb", "Local Time Information");
        attributes.put("00002a10-0000-1000-8000-00805f9b34fb", "Secondary Time Zone");
        attributes.put("00002a11-0000-1000-8000-00805f9b34fb", "Time with DST");
        attributes.put("00002a12-0000-1000-8000-00805f9b34fb", "Time Accuracy");
        attributes.put("00002a13-0000-1000-8000-00805f9b34fb", "Time Source");
        attributes.put("00002a14-0000-1000-8000-00805f9b34fb", "Reference Time Information");
        attributes.put("00002a15-0000-1000-8000-00805f9b34fb", "Time Broadcast");
        attributes.put("00002a16-0000-1000-8000-00805f9b34fb", "Time Update Control Point");
        attributes.put("00002a17-0000-1000-8000-00805f9b34fb", "Time Update State");
        attributes.put("00002a18-0000-1000-8000-00805f9b34fb", "Glucose Measurement");
        attributes.put("00002a19-0000-1000-8000-00805f9b34fb", "Battery Level");
        attributes.put("00002a1a-0000-1000-8000-00805f9b34fb", "Battery Power State");
        attributes.put("00002a1b-0000-1000-8000-00805f9b34fb", "Battery Level State");
        attributes.put("00002a1c-0000-1000-8000-00805f9b34fb", "Temperature Measurement");
        attributes.put("00002a1d-0000-1000-8000-00805f9b34fb", "Temperature Type");
        attributes.put("00002a1e-0000-1000-8000-00805f9b34fb", "Intermediate Temperature");
        attributes.put("00002a1f-0000-1000-8000-00805f9b34fb", "Temperature Celsius");
        attributes.put("00002a20-0000-1000-8000-00805f9b34fb", "Temperature Fahrenheit");
        attributes.put("00002a21-0000-1000-8000-00805f9b34fb", "Measurement Interval");
        attributes.put("00002a22-0000-1000-8000-00805f9b34fb", "Boot Keyboard Input Report");
        attributes.put("00002a23-0000-1000-8000-00805f9b34fb", "System ID");
        attributes.put("00002a24-0000-1000-8000-00805f9b34fb", "Model Number String");
        attributes.put("00002a25-0000-1000-8000-00805f9b34fb", "Serial Number String");
        attributes.put("00002a26-0000-1000-8000-00805f9b34fb", "Firmware Revision String");
        attributes.put("00002a27-0000-1000-8000-00805f9b34fb", "Hardware Revision String");
        attributes.put("00002a28-0000-1000-8000-00805f9b34fb", "Software Revision String");
        attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");
        attributes.put("00002a2a-0000-1000-8000-00805f9b34fb", "IEEE 11073-20601 Regulatory Certification Data List");
        attributes.put("00002a2b-0000-1000-8000-00805f9b34fb", "Current Time");
        attributes.put("00002a2c-0000-1000-8000-00805f9b34fb", "Magnetic Declination");
        attributes.put("00002a2f-0000-1000-8000-00805f9b34fb", "Position 2D");
        attributes.put("00002a30-0000-1000-8000-00805f9b34fb", "Position 3D");
        attributes.put("00002a31-0000-1000-8000-00805f9b34fb", "Scan Refresh");
        attributes.put("00002a32-0000-1000-8000-00805f9b34fb", "Boot Keyboard Output Report");
        attributes.put("00002a33-0000-1000-8000-00805f9b34fb", "Boot Mouse Input Report");
        attributes.put("00002a34-0000-1000-8000-00805f9b34fb", "Glucose Measurement Context");
        attributes.put("00002a35-0000-1000-8000-00805f9b34fb", "Blood Pressure Measurement");
        attributes.put("00002a36-0000-1000-8000-00805f9b34fb", "Intermediate Cuff Pressure");
        attributes.put("00002a37-0000-1000-8000-00805f9b34fb", "Heart Rate Measurement");
        attributes.put("00002a38-0000-1000-8000-00805f9b34fb", "Body Sensor Location");
        attributes.put("00002a39-0000-1000-8000-00805f9b34fb", "Heart Rate Control Point");
        attributes.put("00002a3a-0000-1000-8000-00805f9b34fb", "Removable");
        attributes.put("00002a3b-0000-1000-8000-00805f9b34fb", "Service Required");
        attributes.put("00002a3c-0000-1000-8000-00805f9b34fb", "Scientific Temperature Celsius");
        attributes.put("00002a3d-0000-1000-8000-00805f9b34fb", "String");
        attributes.put("00002a3e-0000-1000-8000-00805f9b34fb", "Network Availability");
        attributes.put("00002a3f-0000-1000-8000-00805f9b34fb", "Alert Status");
        attributes.put("00002a40-0000-1000-8000-00805f9b34fb", "Ringer Control point");
        attributes.put("00002a41-0000-1000-8000-00805f9b34fb", "Ringer Setting");
        attributes.put("00002a42-0000-1000-8000-00805f9b34fb", "Alert Category ID Bit Mask");
        attributes.put("00002a43-0000-1000-8000-00805f9b34fb", "Alert Category ID");
        attributes.put("00002a44-0000-1000-8000-00805f9b34fb", "Alert Notification Control Point");
        attributes.put("00002a45-0000-1000-8000-00805f9b34fb", "Unread Alert Status");
        attributes.put("00002a46-0000-1000-8000-00805f9b34fb", "New Alert");
        attributes.put("00002a47-0000-1000-8000-00805f9b34fb", "Supported New Alert Category");
        attributes.put("00002a48-0000-1000-8000-00805f9b34fb", "Supported Unread Alert Category");
        attributes.put("00002a49-0000-1000-8000-00805f9b34fb", "Blood Pressure Feature");
        attributes.put("00002a4a-0000-1000-8000-00805f9b34fb", "HID Information");
        attributes.put("00002a4b-0000-1000-8000-00805f9b34fb", "Report Map");
        attributes.put("00002a4c-0000-1000-8000-00805f9b34fb", "HID Control Point");
        attributes.put("00002a4d-0000-1000-8000-00805f9b34fb", "Report");
        attributes.put("00002a4e-0000-1000-8000-00805f9b34fb", "Protocol Mode");
        attributes.put("00002a4f-0000-1000-8000-00805f9b34fb", "Scan Interval Window");
        attributes.put("00002a50-0000-1000-8000-00805f9b34fb", "PnP ID");
        attributes.put("00002a51-0000-1000-8000-00805f9b34fb", "Glucose Feature");
        attributes.put("00002a52-0000-1000-8000-00805f9b34fb", "Record Access Control Point");
        attributes.put("00002a53-0000-1000-8000-00805f9b34fb", "RSC Measurement");
        attributes.put("00002a54-0000-1000-8000-00805f9b34fb", "RSC Feature");
        attributes.put("00002a55-0000-1000-8000-00805f9b34fb", "SC Control Point");
        attributes.put("00002a56-0000-1000-8000-00805f9b34fb", "Digital");
        attributes.put("00002a57-0000-1000-8000-00805f9b34fb", "Digital Output");
        attributes.put("00002a58-0000-1000-8000-00805f9b34fb", "Analog");
        attributes.put("00002a59-0000-1000-8000-00805f9b34fb", "Analog Output");
        attributes.put("00002a5a-0000-1000-8000-00805f9b34fb", "Aggregate");
        attributes.put("00002a5b-0000-1000-8000-00805f9b34fb", "CSC Measurement");
        attributes.put("00002a5c-0000-1000-8000-00805f9b34fb", "CSC Feature");
        attributes.put("00002a5d-0000-1000-8000-00805f9b34fb", "Sensor Location");
        attributes.put("00002a5e-0000-1000-8000-00805f9b34fb", "PLX Spot-Check Measurement");
        attributes.put("00002a5f-0000-1000-8000-00805f9b34fb", "PLX Continuous Measurement Characteristic");
        attributes.put("00002a60-0000-1000-8000-00805f9b34fb", "PLX Features");
        attributes.put("00002a62-0000-1000-8000-00805f9b34fb", "Pulse Oximetry Control Point");
        attributes.put("00002a63-0000-1000-8000-00805f9b34fb", "Cycling Power Measurement");
        attributes.put("00002a64-0000-1000-8000-00805f9b34fb", "Cycling Power Vector");
        attributes.put("00002a65-0000-1000-8000-00805f9b34fb", "Cycling Power Feature");
        attributes.put("00002a66-0000-1000-8000-00805f9b34fb", "Cycling Power Control Point");
        attributes.put("00002a67-0000-1000-8000-00805f9b34fb", "Location and Speed Characteristic");
        attributes.put("00002a68-0000-1000-8000-00805f9b34fb", "Navigation");
        attributes.put("00002a69-0000-1000-8000-00805f9b34fb", "Position Quality");
        attributes.put("00002a6a-0000-1000-8000-00805f9b34fb", "LN Feature");
        attributes.put("00002a6b-0000-1000-8000-00805f9b34fb", "LN Control Point");
        attributes.put("00002a6c-0000-1000-8000-00805f9b34fb", "Elevation");
        attributes.put("00002a6d-0000-1000-8000-00805f9b34fb", "Pressure");
        attributes.put("00002a6e-0000-1000-8000-00805f9b34fb", "Temperature");
        attributes.put("00002a6f-0000-1000-8000-00805f9b34fb", "Humidity");
        attributes.put("00002a70-0000-1000-8000-00805f9b34fb", "True Wind Speed");
        attributes.put("00002a71-0000-1000-8000-00805f9b34fb", "True Wind Direction");
        attributes.put("00002a72-0000-1000-8000-00805f9b34fb", "Apparent Wind Speed");
        attributes.put("00002a73-0000-1000-8000-00805f9b34fb", "Apparent Wind Direction");
        attributes.put("00002a74-0000-1000-8000-00805f9b34fb", "Gust Factor");
        attributes.put("00002a75-0000-1000-8000-00805f9b34fb", "Pollen Concentration");
        attributes.put("00002a76-0000-1000-8000-00805f9b34fb", "UV Index");
        attributes.put("00002a77-0000-1000-8000-00805f9b34fb", "Irradiance");
        attributes.put("00002a78-0000-1000-8000-00805f9b34fb", "Rainfall");
        attributes.put("00002a79-0000-1000-8000-00805f9b34fb", "Wind Chill");
        attributes.put("00002a7a-0000-1000-8000-00805f9b34fb", "Heat Index");
        attributes.put("00002a7b-0000-1000-8000-00805f9b34fb", "Dew Point");
        attributes.put("00002a7d-0000-1000-8000-00805f9b34fb", "Descriptor Value Changed");
        attributes.put("00002a7e-0000-1000-8000-00805f9b34fb", "Aerobic Heart Rate Lower Limit");
        attributes.put("00002a7f-0000-1000-8000-00805f9b34fb", "Aerobic Threshold");
        attributes.put("00002a80-0000-1000-8000-00805f9b34fb", "Age");
        attributes.put("00002a81-0000-1000-8000-00805f9b34fb", "Anaerobic Heart Rate Lower Limit");
        attributes.put("00002a82-0000-1000-8000-00805f9b34fb", "Anaerobic Heart Rate Upper Limit");
        attributes.put("00002a83-0000-1000-8000-00805f9b34fb", "Anaerobic Threshold");
        attributes.put("00002a84-0000-1000-8000-00805f9b34fb", "Aerobic Heart Rate Upper Limit");
        attributes.put("00002a85-0000-1000-8000-00805f9b34fb", "Date of Birth");
        attributes.put("00002a86-0000-1000-8000-00805f9b34fb", "Date of Threshold Assessment");
        attributes.put("00002a87-0000-1000-8000-00805f9b34fb", "Email Address");
        attributes.put("00002a88-0000-1000-8000-00805f9b34fb", "Fat Burn Heart Rate Lower Limit");
        attributes.put("00002a89-0000-1000-8000-00805f9b34fb", "Fat Burn Heart Rate Upper Limit");
        attributes.put("00002a8a-0000-1000-8000-00805f9b34fb", "First Name");
        attributes.put("00002a8b-0000-1000-8000-00805f9b34fb", "Five Zone Heart Rate Limits");
        attributes.put("00002a8c-0000-1000-8000-00805f9b34fb", "Gender");
        attributes.put("00002a8d-0000-1000-8000-00805f9b34fb", "Heart Rate Max");
        attributes.put("00002a8e-0000-1000-8000-00805f9b34fb", "Height");
        attributes.put("00002a8f-0000-1000-8000-00805f9b34fb", "Hip Circumference");
        attributes.put("00002a90-0000-1000-8000-00805f9b34fb", "Last Name");
        attributes.put("00002a91-0000-1000-8000-00805f9b34fb", "Maximum Recommended Heart Rate");
        attributes.put("00002a92-0000-1000-8000-00805f9b34fb", "Resting Heart Rate");
        attributes.put("00002a93-0000-1000-8000-00805f9b34fb", "Sport Type for Aerobic and Anaerobic Thresholds");
        attributes.put("00002a94-0000-1000-8000-00805f9b34fb", "Three Zone Heart Rate Limits");
        attributes.put("00002a95-0000-1000-8000-00805f9b34fb", "Two Zone Heart Rate Limit");
        attributes.put("00002a96-0000-1000-8000-00805f9b34fb", "VO2 Max");
        attributes.put("00002a97-0000-1000-8000-00805f9b34fb", "Waist Circumference");
        attributes.put("00002a98-0000-1000-8000-00805f9b34fb", "Weight");
        attributes.put("00002a99-0000-1000-8000-00805f9b34fb", "Database Change Increment");
        attributes.put("00002a9a-0000-1000-8000-00805f9b34fb", "User Index");
        attributes.put("00002a9b-0000-1000-8000-00805f9b34fb", "Body Composition Feature");
        attributes.put("00002a9c-0000-1000-8000-00805f9b34fb", "Body Composition Measurement");
        attributes.put("00002a9d-0000-1000-8000-00805f9b34fb", "Weight Measurement");
        attributes.put("00002a9e-0000-1000-8000-00805f9b34fb", "Weight Scale Feature");
        attributes.put("00002a9f-0000-1000-8000-00805f9b34fb", "User Control Point");
        attributes.put("00002aa0-0000-1000-8000-00805f9b34fb", "Magnetic Flux Density - 2D");
        attributes.put("00002aa1-0000-1000-8000-00805f9b34fb", "Magnetic Flux Density - 3D");
        attributes.put("00002aa2-0000-1000-8000-00805f9b34fb", "Language");
        attributes.put("00002aa3-0000-1000-8000-00805f9b34fb", "Barometric Pressure Trend");
        attributes.put("00002aa4-0000-1000-8000-00805f9b34fb", "Bond Management Control Point");
        attributes.put("00002aa5-0000-1000-8000-00805f9b34fb", "Bond Management Features");
        attributes.put("00002aa6-0000-1000-8000-00805f9b34fb", "Central Address Resolution");
        attributes.put("00002aa7-0000-1000-8000-00805f9b34fb", "CGM Measurement");
        attributes.put("00002aa8-0000-1000-8000-00805f9b34fb", "CGM Feature");
        attributes.put("00002aa9-0000-1000-8000-00805f9b34fb", "CGM Status");
        attributes.put("00002aaa-0000-1000-8000-00805f9b34fb", "CGM Session Start Time");
        attributes.put("00002aab-0000-1000-8000-00805f9b34fb", "CGM Session Run Time");
        attributes.put("00002aac-0000-1000-8000-00805f9b34fb", "CGM Specific Ops Control Point");
        attributes.put("00002aad-0000-1000-8000-00805f9b34fb", "Indoor Positioning Configuration");
        attributes.put("00002aae-0000-1000-8000-00805f9b34fb", "Latitude");
        attributes.put("00002aaf-0000-1000-8000-00805f9b34fb", "Longitude");
        attributes.put("00002ab0-0000-1000-8000-00805f9b34fb", "Local North Coordinate");
        attributes.put("00002ab1-0000-1000-8000-00805f9b34fb", "Local East Coordinate");
        attributes.put("00002ab2-0000-1000-8000-00805f9b34fb", "Floor Number");
        attributes.put("00002ab3-0000-1000-8000-00805f9b34fb", "Altitude");
        attributes.put("00002ab4-0000-1000-8000-00805f9b34fb", "Uncertainty");
        attributes.put("00002ab5-0000-1000-8000-00805f9b34fb", "Location Name");
        attributes.put("00002ab6-0000-1000-8000-00805f9b34fb", "URI");
        attributes.put("00002ab7-0000-1000-8000-00805f9b34fb", "HTTP Headers");
        attributes.put("00002ab8-0000-1000-8000-00805f9b34fb", "HTTP Status Code");
        attributes.put("00002ab9-0000-1000-8000-00805f9b34fb", "HTTP Entity Body");
        attributes.put("00002aba-0000-1000-8000-00805f9b34fb", "HTTP Control Point");
        attributes.put("00002abb-0000-1000-8000-00805f9b34fb", "HTTPS Security");
        attributes.put("00002abc-0000-1000-8000-00805f9b34fb", "TDS Control Point");
        attributes.put("00002abd-0000-1000-8000-00805f9b34fb", "OTS Feature");
        attributes.put("00002abe-0000-1000-8000-00805f9b34fb", "Object Name");
        attributes.put("00002abf-0000-1000-8000-00805f9b34fb", "Object Type");
        attributes.put("00002ac0-0000-1000-8000-00805f9b34fb", "Object Size");
        attributes.put("00002ac1-0000-1000-8000-00805f9b34fb", "Object First-Created");
        attributes.put("00002ac2-0000-1000-8000-00805f9b34fb", "Object Last-Modified");
        attributes.put("00002ac3-0000-1000-8000-00805f9b34fb", "Object ID");
        attributes.put("00002ac4-0000-1000-8000-00805f9b34fb", "Object Properties");
        attributes.put("00002ac5-0000-1000-8000-00805f9b34fb", "Object Action Control Point");
        attributes.put("00002ac6-0000-1000-8000-00805f9b34fb", "Object List Control Point");
        attributes.put("00002ac7-0000-1000-8000-00805f9b34fb", "Object List Filter");
        attributes.put("00002ac8-0000-1000-8000-00805f9b34fb", "Object Changed");
        attributes.put("00002ac9-0000-1000-8000-00805f9b34fb", "Resolvable Private Address Only");
        attributes.put("00002acc-0000-1000-8000-00805f9b34fb", "Fitness Machine Feature");
        attributes.put("00002acd-0000-1000-8000-00805f9b34fb", "Treadmill Data");
        attributes.put("00002ace-0000-1000-8000-00805f9b34fb", "Cross Trainer Data");
        attributes.put("00002acf-0000-1000-8000-00805f9b34fb", "Step Climber Data");
        attributes.put("00002ad0-0000-1000-8000-00805f9b34fb", "Stair Climber Data");
        attributes.put("00002ad1-0000-1000-8000-00805f9b34fb", "Rower Data");
        attributes.put("00002ad2-0000-1000-8000-00805f9b34fb", "Indoor Bike Data");
        attributes.put("00002ad3-0000-1000-8000-00805f9b34fb", "Training Status");
        attributes.put("00002ad4-0000-1000-8000-00805f9b34fb", "Supported Speed Range");
        attributes.put("00002ad5-0000-1000-8000-00805f9b34fb", "Supported Inclination Range");
        attributes.put("00002ad6-0000-1000-8000-00805f9b34fb", "Supported Resistance Level Range");
        attributes.put("00002ad7-0000-1000-8000-00805f9b34fb", "Supported Heart Rate Range");
        attributes.put("00002ad8-0000-1000-8000-00805f9b34fb", "Supported Power Range");
        attributes.put("00002ad9-0000-1000-8000-00805f9b34fb", "Fitness Machine Control Point");
        attributes.put("00002ada-0000-1000-8000-00805f9b34fb", "Fitness Machine Status");
        attributes.put("00002aed-0000-1000-8000-00805f9b34fb", "Date UTC");
        attributes.put("00002b1d-0000-1000-8000-00805f9b34fb", "RC Feature");
        attributes.put("00002b1e-0000-1000-8000-00805f9b34fb", "RC Settings");
        attributes.put("00002b1f-0000-1000-8000-00805f9b34fb", "Reconnection Configuration Control Point");

        //Vlogger
        attributes.put(VLOGGER_DEVELOPER, "CAN bus logger");
        attributes.put(VLOGGER_DEVELOPER_RX,"CAN RECEIVE");
        attributes.put(VLOGGER_DEVELOPER_TX,"CAN NOTIFY");
        attributes.put(VLOGGER_DEVELOPER_CLIENT,"Client uart characteristic");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
